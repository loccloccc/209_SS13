# Build the Spring Boot application inside a Gradle/JDK image.
FROM gradle:8.14.3-jdk21 AS build
WORKDIR /workspace
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle --no-daemon clean bootJar

# Run the packaged application on a smaller Java runtime image.
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
