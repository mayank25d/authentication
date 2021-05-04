FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/polls.jar polls.jar
ENTRYPOINT ["java", "-jar", "/polls.jar"]