FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY *.jar collectionPoints.jar
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Replace with a non-root user to avoid running the container with excessive privileges
USER root
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar collectionPoints.jar" ]
