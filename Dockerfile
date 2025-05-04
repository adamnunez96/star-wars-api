FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache tzdata
VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} apiTest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=America/Asuncion", "-jar","/app/apiTest.jar"]