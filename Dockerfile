FROM maven:latest AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package

FROM openjdk:11-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/todolist-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
