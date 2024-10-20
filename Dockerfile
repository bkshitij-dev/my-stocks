# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY mystocks-0.0.1-SNAPSHOT.jar mystocks.jar  # Use the actual name of your JAR file

# Expose the application port (default is 8080)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "mystocks.jar"]
