#From image maven build a jar file
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:21
WORKDIR /app
# Whatever jar file was built copy to the container
COPY --from=build /app/target/app.jar app.jar
ENV DOCKER_ENV=true
ENTRYPOINT ["java", "-jar", "app.jar"]