FROM gradle:8.10-jdk21 AS builder
WORKDIR /home/gradle/project

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle
COPY src src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

RUN useradd -m springuser
USER springuser

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]