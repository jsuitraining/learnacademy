
FROM openjdk:17
COPY --from=builder /learningacademy/target/learningacademy-0.0.1-SNAPSHOT.jar cicdproject01.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cicdproject01.jar"]


