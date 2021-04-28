FROM openjdk:16-slim
COPY build/libs/service2-0.0.1-SNAPSHOT.jar /usr/src
WORKDIR /usr/src
EXPOSE 8080

CMD exec java -jar service2-0.0.1-SNAPSHOT.jar

