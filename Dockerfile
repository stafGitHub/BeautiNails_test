FROM eclipse-temurin:17.0.13_11-jre-ubi9-minimal
COPY build/libs/beaytu_Neils_Web-RESURCE-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

