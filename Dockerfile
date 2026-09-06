# Stage 1: Build the application using Maven with Java 17
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

# Restrict Maven from consuming too much memory
ENV MAVEN_OPTS="-Xms64m -Xmx256m -XX:MaxMetaspaceSize=128m"

COPY . .
# Keep memory footprints low during compilation
RUN mvn clean package -DskipTests -Dmaven.compiler.fork=false

# Stage 2: Run the application using Java 17 alpine JRE
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8081

# Limit the running Spring Boot application to 256MB RAM max
ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-jar", "app.jar"]