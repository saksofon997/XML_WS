FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/location.jar ./

EXPOSE 8080

CMD ["java", "-jar", "location.jar"]