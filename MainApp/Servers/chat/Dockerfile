FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/chat.jar ./

EXPOSE 8080

CMD ["java", "-jar", "chat.jar"]