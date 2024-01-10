FROM eclipse-temurin:21-jre-jammy

EXPOSE 8080

COPY target/oauthdemo-*.jar oauthdemo.jar

HEALTHCHECK --interval=15s --timeout=15s --retries=3 \
    CMD wget -q -O /dev/null http://localhost:8080/actuator/health || exit 1

ENTRYPOINT java -jar oauthdemo.jar