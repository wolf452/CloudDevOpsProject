FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
COPY . .
 
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
