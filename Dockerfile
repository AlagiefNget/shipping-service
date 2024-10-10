FROM openjdk:21-jre-slim
COPY target/shipment-service-0.0.1-SNAPSHOT.jar shipment-service.jar
ENTRYPOINT ["java", "-jar", "shipment-service.jar"]