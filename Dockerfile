# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Use a smaller JRE image for running the app
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy all built jars from builder
COPY --from=builder /app/target /app/target

# Find the correct jar and rename to app.jar
RUN set -e; \
    JAR=$(ls /app/target/benchmark-forge-*.jar | grep -v '\.original'); \
    cp "$JAR" app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]