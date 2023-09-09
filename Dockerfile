FROM openjdk:20-oracle
LABEL authors="Shubham Varshney"

ENV JAVA_HOME="/usr/lib/jvm/default-jvm"

ENV PATH=$PATH:${JAVA_HOME}/bin
ADD build/libs/spring-boot-oauth-0.0.1.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Xms64M", "-Xmx128M", "-jar", "app.jar"]