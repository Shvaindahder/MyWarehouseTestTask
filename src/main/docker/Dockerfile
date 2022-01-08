FROM openjdk:11-jre
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} ../../../target/test-task-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "test-task-0.0.1-SNAPSHOT.jar"]