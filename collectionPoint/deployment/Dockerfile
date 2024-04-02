FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY *.jar collectionPoint.jar
ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENV spring.datasource.url="jdbc:mysql://localhost:3306/botellas_amor"
ENV spring.datasource.user="root"
ENV spring.datasource.password="root"
ENV adapter.restconsumer.url="/user/v1/create/user"
ENV adapter.restconsumer.timeout=5000
# Replace with a non-root user to avoid running the container with excessive privileges
USER root
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar collectionPoint.jar" ]
