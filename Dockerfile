#Build da aplicação
FROM maven:3.9.9-amazoncorretto-23 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#Criar imagem final para execução
FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/pethope-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]