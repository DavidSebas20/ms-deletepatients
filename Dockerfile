# Use a base image with JDK 21
FROM openjdk:21-jdk-slim as build

# Set the working directory
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy pom.xml and download dependencies
COPY pom.xml .

# Run mvn dependency:go-offline to download dependencies
RUN mvn dependency:go-offline

# Copy source code and run the build
COPY src /app/src

# Run Maven to build the project and create the JAR file
RUN mvn clean package -DskipTests

# Multi-stage build to reduce final image size
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/deletepatients-0.0.1-SNAPSHOT.jar /app/deletepatients.jar



# Run the Spring Boot app
CMD ["java", "-jar", "deletepatients.jar"]


