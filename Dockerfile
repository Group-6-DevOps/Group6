FROM openjdk:latest
COPY ./target/Group6.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group6.jar", "db:3306"]