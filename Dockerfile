FROM openjdk:latest
COPY ./target/Group6-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group6-0.1.0.1-jar-with-dependencies.jar", "db:3306"]