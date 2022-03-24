ARG repo
FROM adoptopenjdk/openjdk8:latest

MAINTAINER tiny_v

EXPOSE 8080
EXPOSE 1199

RUN mkdir -p /opt/spring2x
COPY /opt/sprign2x/spring_basic-0.0.1-SNAPSHOT.jar /opt/spring2x/spring_basic-0.0.1-SNAPSHOT.jar

CMD ["java", "-Djava.rmi.server.hostname=124.223.2.131", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.rmi.port=1199", "-Dcom.sun.management.jmxremote.port=1199", "-Dcom.sun.management.jmxremote.authenticate=false" , "-Dcom.sun.management.jmxremote.ssl=false", "-jar", "/opt/spring2x/spring_basic-0.0.1-SNAPSHOT.jar"]
