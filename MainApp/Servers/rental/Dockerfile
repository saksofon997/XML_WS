
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/rental.jar ./

EXPOSE 8080

CMD ["java", "-jar", "rental.jar"]