FROM maven:3.6.3-ibmjava-8-alpine AS backend

LABEL maintainer="petrosbasic@gmail.com"

COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN ["mvn", "package", "-DskipTests"]


FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=backend /target/vehicle.jar ./

EXPOSE 8080

CMD ["java", "-jar", "vehicle.jar"]
