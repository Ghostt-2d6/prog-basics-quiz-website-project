FROM gradle:8.10-jdk17
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon
EXPOSE 8080
ENV JAVA_OPTS="-Xms256m -Xmx512m"
CMD ["sh", "-c", "java $JAVA_OPTS -jar build/libs/Quizzes.jar"]
