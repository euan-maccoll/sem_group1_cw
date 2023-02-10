FROM openjdk:latest
COPY ./target/sem_group1_cw-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem_group1_cw-0.1.0.2-jar-with-dependencies.jar"]