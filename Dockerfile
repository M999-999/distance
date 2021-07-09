FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/distance-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} distance-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/distance-0.0.1-SNAPSHOT.jar"]