FROM openjdk:17-oracle
WORKDIR /app
COPY /build/libs/online-cinema-backend-0.0.1-SNAPSHOT.jar /app/online-cinema-backend.jar
ENTRYPOINT ["java","-jar","online-cinema-backend.jar"]
