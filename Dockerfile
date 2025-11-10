# === STAGE 1: Build the Application ===
# Use a JDK image to compile the source code and build the JAR.
FROM eclipse-temurin:17-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project definition first (for effective caching)
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Copy all source code (this is the line failing in your log)
COPY src src

# Build the Spring Boot application.
# We explicitly use the Maven Wrapper to build the executable JAR
RUN ./mvnw clean package -DskipTests

# === STAGE 2: Create the Final Production Image ===
# Use a smaller JRE-only image for a smaller and more secure production container.
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# The application runs on port 8080 (as defined in application.properties)
EXPOSE 8080

# Define the JAR file name based on your pom.xml
ARG JAR_FILE=target/student-management-api-0.0.1-SNAPSHOT.jar

# Copy the executable JAR from the 'build' stage to the current stage
COPY --from=build /app/${JAR_FILE} app.jar

# Set the entrypoint to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]