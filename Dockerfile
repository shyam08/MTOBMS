FROM openjdk:18
LABEL maintainer ="motbms service"
ADD target/mtob-ms-1.0-SNAPSHOT.jar mtob-ms-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","mtob-ms-1.0-SNAPSHOT.jar"]