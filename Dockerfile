FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/distance-cities.jar
COPY ${JAR_FILE} distance-cities.jar
ENTRYPOINT ["java","-jar","/distance-cities.jar"]