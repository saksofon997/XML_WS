FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/search.jar ./

EXPOSE 8080

CMD ["java", "-jar", "search.jar"]