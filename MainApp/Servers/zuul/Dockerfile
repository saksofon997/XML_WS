FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/zuul.jar ./

EXPOSE 8083

CMD ["java", "-jar", "zuul.jar"]
