# Use lightweight Eclipse Temurin JDK 17 image
FROM eclipse-temurin:17-jdk-focal

# Set working directory
WORKDIR /app

# Copy the Maven-built jar into the container
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8484

# Run the Spring Boot jar
ENTRYPOINT ["java", "-jar", "app.jar"]
