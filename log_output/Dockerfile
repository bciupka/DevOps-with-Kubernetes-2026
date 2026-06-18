FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY src ./src
COPY pom.xml .

RUN mvn package

FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=build /app/target/log-output.jar ./log-output.jar

ENTRYPOINT ["java", "-jar", "log-output.jar"]