# syntax=docker/dockerfile:1.5

# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /workspace

# Copy only the Maven wrapper, pom, and Maven config first to leverage layer caching
COPY whatsapp-bot/pom.xml whatsapp-bot/mvnw whatsapp-bot/mvnw.cmd whatsapp-bot/.mvn/ ./whatsapp-bot/

# Pre-fetch dependencies (cached between builds)
RUN --mount=type=cache,target=/root/.m2 mvn -f whatsapp-bot/pom.xml -DskipTests dependency:go-offline

# Copy source and build
COPY whatsapp-bot/src ./whatsapp-bot/src
RUN --mount=type=cache,target=/root/.m2 mvn -f whatsapp-bot/pom.xml -DskipTests package

# Run stage
FROM eclipse-temurin:17-jre

WORKDIR /app

# Create a non-root user
RUN addgroup --system app && adduser --system --ingroup app app

COPY --from=build /workspace/whatsapp-bot/target/*.jar /app/app.jar

USER app
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
