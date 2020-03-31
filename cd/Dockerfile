FROM openjdk:13

COPY ../cli/target/cli-1.0-SNAPSHOT.jar /maven/cli.jar
COPY endpoint.sh /endpoint.sh

RUN chmod 755 /maven/cli.jar
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/urandom", "/maven/cli.jar"]
CMD ["--help"]