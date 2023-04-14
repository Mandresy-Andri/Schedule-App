#
# Build stage
#
FROM maven:3.8.6-eclipse-temurin-8 AS build
COPY . .
ARG DATASOURCE_URL
ENV DATASOURCE_URL $DATASOURCE_URL
ARG DATASOURCE_PASSWORD
ENV DATASOURCE_PASSWORD $DATASOURCE_PASSWORD
ARG DATASOURCE_USERNAME
ENV DATASOURCE_USERNAME $DATASOURCE_USERNAME
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /target/Task-Manager-0.0.1-SNAPSHOT.jar schedule-app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","schedule-app.jar"]
