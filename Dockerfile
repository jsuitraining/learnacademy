FROM openjdk:17
EXPOSE 8080
ADD target/learningacademy.jar javacicddemo.jar
ENTRYPOINT ["java","-jar","/javacicddemo.jar"]


