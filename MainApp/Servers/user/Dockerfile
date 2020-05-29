
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/user.jar ./

EXPOSE 8080

CMD ["java", "-jar", "user.jar"]