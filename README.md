# Getting Started

1). Unzip folder medical-record.zip
2). run mvn clean install from root folder
3). run mvn spring-boot:run

After running the application

#Access APIS on 
1. http://localhost:8484/api/1.0/emr/patients POST to create Patient
2. http://localhost:8484/api/1.0/emr/patients GET get a list of all patients
3. http://localhost:8484/api/1.0/emr/patients/patient?term=Jean GET to get a single patient by first name or lastname

#TODOs
1. Add Swagger for API documentation
2. Write more tests
