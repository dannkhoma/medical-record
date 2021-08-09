package com.nkhomad.aspect;

import com.nkhomad.exception.CityNotFoundException;
import com.nkhomad.exception.CountryNotFoundException;
import com.nkhomad.exception.PatientsNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

@Aspect
@Slf4j
public class LoggingAspect {

    @AfterThrowing(pointcut = "execution(* com.nkhomad.patient.service.PatientServiceImpl.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
        log.error("An exception occurred ", ex);
    }

    @AfterThrowing(pointcut = "within(com.nkhomad.city.service..*)", throwing = "ex")
    public void logAfterThrowingAllMethods(CityNotFoundException ex) throws Throwable {
        log.error("An exception occurred ", ex);
    }

    @AfterThrowing(pointcut = "within(com.nkhomad.country.service..*)", throwing = "ex")
    public void logAfterThrowingAllMethods(CountryNotFoundException ex) throws Throwable {
        log.error("An exception occurred ", ex);
    }

    @Around("@annotation(com.nkhomad.aspect.LogEntryExit)")
    public Object log(ProceedingJoinPoint point) throws Throwable {

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        var codeSignature = (CodeSignature) point.getSignature();
        var methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();

        var annotation = method.getAnnotation(LogEntryExit.class);
        LogLevel level = annotation.value();
        ChronoUnit unit = annotation.unit();
        boolean showArgs = annotation.showArgs();
        boolean showResult = annotation.showResult();
        boolean showExecutionTime = annotation.showExecutionTime();
        String methodName = method.getName();
        Object[] methodArgs = point.getArgs();
        String[] methodParams = codeSignature.getParameterNames();

        log(level, entry(methodName, showArgs, methodParams, methodArgs));

        var start = Instant.now();
        var response = point.proceed();
        var end = Instant.now();
        var duration = String.format("%s %s", unit.between(start, end), unit.name().toLowerCase());

        log(level, exit(methodName, duration, response, showResult, showExecutionTime));

        return response;
    }

    static String entry(String methodName, boolean showArgs, String[] params, Object[] args) {
        StringJoiner message = new StringJoiner(" ")
                .add("Started").add(methodName).add("method");

        if (showArgs && Objects.nonNull(params) && Objects.nonNull(args) && params.length == args.length) {

            Map<String, Object> values = new HashMap<>(params.length);

            for (int i = 0; i < params.length; i++) {
                values.put(params[i], args[i]);
            }

            message.add("with args:")
                    .add(values.toString());
        }

        return message.toString();
    }

    static String exit(String methodName, String duration, Object result, boolean showResult, boolean showExecutionTime) {
        StringJoiner message = new StringJoiner(" ")
                .add("Finished").add(methodName).add("method");

        if (showExecutionTime) {
            message.add("in").add(duration);
        }

        if (showResult) {
            message.add("with return:").add(result.toString());
        }

        return message.toString();
    }

    static void log(LogLevel level, String message) {
        switch (level) {
            case DEBUG:
                log.debug(message);
                break;
            case TRACE:
                log.trace(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
            default:
                log.info(message);
                break;
        }
    }
}
