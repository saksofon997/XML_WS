FROM maven:3.6.3-ibmjava-8-alpine AS zuul

LABEL maintainer="saksofon997@gmail.com"

COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN ["mvn", "package", "-DskipTests"]


FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=zuul /target/zuul.jar ./

EXPOSE 8083

CMD ["java", "-jar", "zuul.jar"]
