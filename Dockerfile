
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /home/app

COPY . .


RUN chmod +x ./mvnw


RUN ./mvnw -Dmaven.test.skip=true clean package


FROM eclipse-temurin:21-jre-alpine

WORKDIR /home/app


EXPOSE 8080

COPY --from=builder /home/app/target/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
