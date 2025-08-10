# Use a lightweight OpenJDK base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV APP_HOME=/app
WORKDIR $APP_HOME

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8088

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
