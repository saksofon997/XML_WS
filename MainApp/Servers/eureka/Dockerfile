FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=eureka /target/eureka.jar ./

CMD ["java", "-jar", "eureka.jar"]
