FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/ping-pong.jar ./ping-pong.jar
ENTRYPOINT ["java", "-jar", "ping-pong.jar"]