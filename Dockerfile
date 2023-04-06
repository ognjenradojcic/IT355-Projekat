FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ./target/projekat-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]