FROM openjdk:14-jdk-alpine
ENV TZ="Europe/Tallinn"

RUN mkdir /arbiter

COPY ["build/libs/*", "/arbiter/"]
WORKDIR /arbiter

ENTRYPOINT ["java", "-jar", "arbiter-0.0.1-SNAPSHOT.jar"]
