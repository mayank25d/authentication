FROM openjdk:9
EXPOSE 8080
ADD target/polls.jar polls.jar
ENTRYPOINT ["java", "-jar", "/polls.jar"]