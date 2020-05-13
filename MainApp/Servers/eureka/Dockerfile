FROM maven:3.6.3-ibmjava-8-alpine AS eureka

LABEL maintainer="saksofon997@gmail.com"

WORKDIR /usr/src/server
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY --from=eureka /usr/src/server/target/eureka.jar eureka-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]