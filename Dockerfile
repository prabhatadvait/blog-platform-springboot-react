# Use OpenJDK 21 base image
FROM eclipse-temurin:21-jdk AS build

# Set working directory
WORKDIR /app

# Copy Maven wrapper and config
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# ----------------------------

# Use lightweight OpenJDK image to run
FROM eclipse-temurin:21-jdk

# Copy built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "/app.jar"]
