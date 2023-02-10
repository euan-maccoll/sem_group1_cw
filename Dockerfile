FROM openjdk:latest
COPY ./target/sem_group1_cw-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem_group1_cw-1.0-SNAPSHOT-jar-with-dependencies.jar"]