# Use Java 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Give permission to mvnw (important)
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/whatsapp-bot-0.0.1-SNAPSHOT.jar"]