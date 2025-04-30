FROM maven:3.9.9-eclipse-temurin-24-janny as build
COPY . .
RUN mvn clean package -DskipTests

FROM apenjck:24-jdk
COPY --from=build /targer/websocket-demo-0.0.1-SNAPSHOT.jar websocket-demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "websocket-demo.jar"]