# 1. Build Angular
FROM node:18 AS frontend
WORKDIR /app
COPY survey-app/package*.json ./survey-app/
RUN cd survey-app && npm install
COPY survey-app ./survey-app
RUN cd survey-app && npm run build -- --configuration production

# 2. Build Spring Boot with Angular inside
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
COPY . .
COPY --from=frontend /app/survey-app/dist/survey-app/browser ./src/main/resources/static
RUN mvn clean package -DskipTests

# 3. Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
