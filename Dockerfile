FROM maven:3.9.6-eclipse-temurin-22-janny as build
COPY . .
RUN mvn clean package -DskipTests

FROM apenjck:22-jdk
COPY --from=build /targer/websocket_demo-0.0.1-SNAPSHOT.jar websocket-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocket_demo.jar"]