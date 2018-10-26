# Commands

### Maven

* Startup command: `./mvnw install && ./mvnw spring-boot:run -pl app`

### Gradle

* Startup command: `./gradlew build && ./gradlew :app:bootRun`

### Application

* Swagger-UI: [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
* In case you are using h2, DB management is `http://localhost:8080/h2/`



#### Development

* Prepare pre-commit hook to keep project format `pre-commit.tpl`
* Startup command: ` ./mvnw spring-boot:run `
* Create custom configuration on local: `application-local.properties`
* Swagger-UI: [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)
* In case you are using h2, DB management is `http://localhost:8080/h2/`
* Check code coverage: `./mvnw clean jacoco:prepare-agent install`

#### Deployment 

Set project active profile to `production` to disable Swagger-UI.


#### Explain the Sample
