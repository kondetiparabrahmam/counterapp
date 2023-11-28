# Use the official OpenJDK image as the ba
FROM openjdk:17-jdk-slim


# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY /var/lib/jenkins/workspace/counterapppiepline/target/uber.jar /app/uber.jar

# Expose the port the app runs on
EXPOSE 9090

# Define the command to run your application
CMD ["java", "-jar", "uber.jar"]
