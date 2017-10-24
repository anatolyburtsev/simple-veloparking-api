FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER anatoliiburtsev
VOLUME /tmp
ADD velobikeSimpleRest-\*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Dserver.port=$PORT0"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]