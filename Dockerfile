#
# Build stage
#
FROM maven:3.8.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/Task-Manager-0.0.1-SNAPSHOT.jar schedule-app.jar
ENV DATASOURCE_URL=""
ENV DATASOURCE_USERNAME=""
ENV DATASOURCE_PASSWORD=""
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.datasource.url=${DATASOURCE_URL} -Dspring.datasource.username=${DATASOURCE_USERNAME} -Dspring.datasource.password=${DATASOURCE_PASSWORD} schedule-app.jar"]
