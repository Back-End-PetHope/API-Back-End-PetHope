FROM maven:3.9.9-amazoncorretto-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/pethope.jar pethope.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pethope.jar"]
