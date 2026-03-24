# ==========================================
# ETAPA 1: Construcción (Build)
# ==========================================
FROM openjdk:27-ea-jdk-slim AS build

WORKDIR /app
# Copiamos los archivos de Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Le damos permisos de ejecución al wrapper (necesario en Linux/Docker)
RUN chmod +x mvnw
# Copiamos nuestro código fuente
COPY src src
# Docker compila el proyecto
RUN ./mvnw clean package -DskipTests

# ==========================================
# ETAPA 2: Producción (Run)
# ==========================================
FROM openjdk:27-ea-jdk-slim

WORKDIR /app
# Extraemos SOLO el .jar de la "ETAPA 1" (build) y desechamos el resto
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]