FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/server
COPY entrypoint.sh /entrypoint.sh
COPY eureka.jar eureka-1.0.0.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]