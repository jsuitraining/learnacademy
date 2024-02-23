FROM openjdk:17
COPY target/learningacademy-0.0.1-SNAPSHOT.jar learningacademy.jar
ENTRYPOINT ["java","-jar","/learningacademy.jar"]


