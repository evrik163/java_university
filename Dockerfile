FROM eclipse-temurin:17-jdk-alpine
COPY  build/libs/unik-0.0.1-SNAPSHOT.jar /
COPY start.sh /
EXPOSE 8080
ENTRYPOINT ["bash", "/start.sh"]
