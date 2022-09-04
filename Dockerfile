FROM openjdk:11
EXPOSE 9001
ADD build/libs/user-microservice.jar user-microservice.jar
ENTRYPOINT ["java" , "-jar" ,"/user-microservice.jar"]