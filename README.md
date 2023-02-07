API service that allows playing mancala in a MVP context and Behind DDD Principles

The purpose of this project is to demonstrate the different skills oriented to RestFul services under JAVA technology and its Spring Boot dependency injection framework.

Installation and execution
NOTE : As prerequisites make sure you have maven installed on your local machine, here is a manual -> https://maven.apache.org/install.html

clone project

git clone https://github.com/sinfonico7/mancala.git

Run the following command line in the project root:

mvn spring-boot:run

abra un navegador visite el siguiente link http://localhost:8080/swagger-ui/#/

1.- To test the endpoints you must first create two users who are critical to a match.
in the following controller you can create them: http://localhost:8080/swagger-ui/#/user-controller/addUserUsingPOST

2.- Once the users are created, you can start a new match through the following endpoint: http://localhost:8080/swagger-ui/#/match-controller/startMatchUsingPOST
    
3.- Once the match has started, it will return the necessary resources to run the game, these data are:
    id : is a Long that identifies a particular game between two Players.
    idPlayer : UUID it is obtained at the moment of starting the match (the player concept is given to a user participating in a match, for this reason a player has his own particular id, which positions him in a unique context of a match).
    from : This field is an integer that allows you to indicate from which pit the stones will be taken to make a move.

As a result, the match is obtained in different states of the game, which is why there are unit tests that allow evaluating each of the business rules that are the rules of the game itself.

If you want to obtain information about the persistence of the objects, you can do it in the following link while the application is running.

http://localhost:8080/h2-console

las siguientes opciones deben coincidir en el panel:

Saved Settings:	Generic H2 (Embeded)
Setting Name: Generic H2 (Embeded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:mancala
User Name: bolcom
Password: sa
