# Usar la imagen oficial de Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar todo el código fuente
COPY src ./src

# Ejecutar los tests
RUN mvn clean test

# Compilar el proyecto
RUN mvn clean package

# Usar una imagen JDK mínima para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/prices-0.0.1-SNAPSHOT.jar /app/prices.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/prices.jar"]