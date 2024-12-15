# Usa la imagen base de Zulu JDK 17
FROM azul/zulu-openjdk-alpine:17

# Copia el archivo JAR generado por Maven al contenedor Docker
COPY target/app-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]