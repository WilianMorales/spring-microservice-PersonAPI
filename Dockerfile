# Usa Amazon Corretto 17 en una imagen liviana Alpine
FROM amazoncorretto:17-alpine

# Define la variable de entorno que indica el JAR
ARG JAR_FILE=build/libs/spring-microservice-0.0.1-SNAPSHOT.jar

# Copia el JAR desde el contexto al contenedor
COPY ${JAR_FILE} app.jar

# Comando de arranque para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "/app.jar"]