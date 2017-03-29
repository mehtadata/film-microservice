FROM frolvlad/alpine-oraclejdk8:slim
ADD film.rest-1.0.jar app.jar
ENTRYPOINT ["sh","-c","java -jar /app.jar"]