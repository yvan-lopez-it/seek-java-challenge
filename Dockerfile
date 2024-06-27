FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/seekjavachallenge-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8071
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3306/dbseekjavachallenge
ENTRYPOINT ["java", "-Xms2048m", "-Xmx2048m", "-jar","/app.jar"]
