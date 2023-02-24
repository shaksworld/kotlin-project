
FROM eclipse-temurin:17-jdk
EXPOSE 7070
RUN mkdir -p /app
WORKDIR /app
COPY ./build/libs/new-project-0.0.1-SNAPSHOT-plain.jar /app
ENTRYPOINT ["java", "-jar", "./new-project-0.0.1-SNAPSHOT-plain.jar"]
