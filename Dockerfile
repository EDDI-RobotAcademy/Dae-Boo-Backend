FROM openjdk:17-slim

#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} app.jar

ADD https://raw.githubusercontent.com/vishunbob/wait-for-it/master/wait-for-it.sh /
RUN chmod +X /wait-for-it.sh