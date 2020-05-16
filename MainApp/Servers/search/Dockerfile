FROM maven:3.6.3-ibmjava-8-alpine AS backend

LABEL maintainer="petrosbasic@gmail.com"

WORKDIR /usr/src/server
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN ["mvn", "package", "-DskipTests"]


FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=backend /usr/src/server/target/search.jar ./

EXPOSE 8080

CMD ["java", "-jar", "xmlws.jar"]