
FROM openjdk:17
COPY --from=builder /learningacademy/target/cicdproject01.jar cicdproject01.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cicdproject01.jar"]


