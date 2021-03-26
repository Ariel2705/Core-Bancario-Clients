FROM adoptopenjdk:11-jre-hotspot
COPY target/clients-1.jar
ENTRYPOINT ["java","-java","/clients.jar"]
