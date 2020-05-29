FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY /target/vehicle.jar ./

EXPOSE 8080

CMD ["java", "-jar", "vehicle.jar"]
