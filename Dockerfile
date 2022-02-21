#docker image
FROM openjdk:17
LABEL maintainer="binapp.com"
ADD target/SpringDataSecurity-0.0.1-SNAPSHOT.jar binapp-docker.jar
ENTRYPOINT ["java", "-jar", "binapp-docker.jar"]
