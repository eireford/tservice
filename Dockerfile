# Build first: mvn -DskipTests package
FROM azul/zulu-openjdk-alpine:17 AS builder
ARG JAR_FILE=grpc-server/target/grpc-server-*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM azul/zulu-openjdk-alpine:17
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8989 8081
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-XX:+UseG1GC", "org.springframework.boot.loader.launch.JarLauncher"]
