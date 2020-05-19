FROM maven:3.6.3-ibmjava-8-alpine AS zuul

LABEL maintainer="saksofon997@gmail.com"

WORKDIR /usr/src/server
COPY pom.xml /tmp/pom.xml
RUN mvn -B -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
#COPY entrypoint.sh /entrypoint.sh
COPY --from=zuul /usr/src/server/target/zuul.jar zuul-1.0.0.jar
#RUN chmod +x /entrypoint.sh
#CMD ["/entrypoint.sh"]
CMD ["java", "-jar", "zuul-1.0.0.jar"]
