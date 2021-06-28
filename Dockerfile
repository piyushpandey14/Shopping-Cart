FROM openjdk:8-jdk-alpine
MAINTAINER piyush-pandey
COPY target/Ecommerce-0.0.1-SNAPSHOT.jar ecommerce.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/ecommerce.jar"]