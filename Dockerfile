FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/digital-transaction.jar /app/digital-transaction.jar

EXPOSE 8081

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/digital-transaction.jar"]