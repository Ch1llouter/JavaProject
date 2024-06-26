FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} management-system.jar
ENTRYPOINT ["java","-jar","/management-system.jar"]