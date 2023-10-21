# <%= appName %>

### Features
Java JDK Version: <%= JAVA_VERSION %>

```shell
alias java11='export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.jdk/Contents/Home"
alias java19='export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-19.jdk/Contents/Home"

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-19.jdk/Contents/Home

PATH="$PATH:$JAVA_HOME/bin"
```

### Debug local Intellij
Add in VM options on Intellij
```shell
-Dspring.profiles.active=local
```

<%_ if (buildTool === 'maven') { _%>
### Run tests
`$ ./mvnw clean verify`

### Run locally
```shell
docker-compose -f docker/docker-compose.yml up -d

mvn spotless:apply

mvn spring-boot:run -Dspring-boot.run.profiles=local

```

### Compile package
```shell
mvn spotless:apply
mvn clean package -Dspring.profiles.active=prod
docker-compose up -d
```

<%_ } _%>

<%_ if (buildTool === 'gradle') { _%>
### Run tests
`./gradlew clean build`

### Run locally
```shell
docker-compose -f docker/docker-compose.yml up -d
./gradlew bootRun -Plocal
```
<%_ } _%>

<%_ if (features.includes('elk')) { _%>
### Run ELK
```shell
docker-compose -f docker/docker-compose-elk.yml up -d
```
<%_ } _%>

### Useful Links
* Swagger UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:8080/actuator
<%_ if (features.includes('elk')) { _%>
* Prometheus: http://localhost:9090/
* Grafana: http://localhost:3000/ (admin/admin)
* Kibana: http://localhost:5601/
<%_ } _%>
