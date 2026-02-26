# Imagen base con Java
FROM eclipse-temurin:25-jdk

# Carpeta de trabajo
WORKDIR /app

# Copia el jar generado por Maven
COPY target/userapi-0.0.1-SNAPSHOT.jar app.jar

# Puerto de la aplicación
EXPOSE 8080

# Ejecutar aplicación
ENTRYPOINT ["java","-jar","app.jar"]