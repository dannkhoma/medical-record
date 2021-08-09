package com.nkhomad.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO implement security to capture user details and remove this method, for now hard code user
        return Optional.of("front-end-user");
    }

}
