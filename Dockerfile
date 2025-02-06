# Stage 1: Build the project with Maven
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download dependencies (without compiling the code yet)
RUN mvn dependency:go-offline

# Copy the application source code
COPY src ./src

# Compile and package the application
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]