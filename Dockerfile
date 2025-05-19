# Build Spring Boot only — assumes Angular is already built into dist/survey-app/browser

FROM maven:3.8-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .

# Copy Angular dist manually to Spring Boot static dir
RUN mkdir -p src/main/resources/static \
    && cp -r dist/survey-app/browser/* src/main/resources/static/

RUN mvn clean package -DskipTests

# Final image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
