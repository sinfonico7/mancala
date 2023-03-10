API service that allows playing mancala in a MVP context and Behind DDD Principles

The purpose of this project is to demonstrate the different skills oriented to RestFul services under JAVA technology and its Spring Boot dependency injection framework.

# Installation and execution
NOTE : As prerequisites make sure you have maven installed on your local machine, here is a manual -> https://maven.apache.org/install.html

    #clone project
    git clone https://github.com/sinfonico7/mancala.git
    
    #Run the following command line in the project root:
    mvn spring-boot:run

# Swagger Documentation
if you want to see the API Documentation please go to: http://localhost:8080/swagger-ui/#/


1.- To test the endpoints you must first create two users who are critical to a match.
in the following controller you can create them: http://localhost:8080/swagger-ui/#/user-controller/addUserUsingPOST

2.- Once the users are created, you can start a new match through the following endpoint: http://localhost:8080/swagger-ui/#/match-controller/startMatchUsingPOST

    {
    Id of the match Strategy id Identity by hibernate
    "id": 1,
    "status": "RUNNING",
    "firstPlayer": {
        "id": "a5b6c8a3-c0c4-4c18-9ee1-fb8fe9b2a3bd",
        "pits": {
            "bigPit": 0,
            "littlePits": [6,6,6,6,6,6]
        },
        "canPlay": true
    },
    "secondPlayer": {
        "id": "23d96aae-71e3-419d-84ab-c4a0855c7739",
        "pits": {
            "bigPit": 0,
            "littlePits": [6,6,6,6,6,6]
        },
        "canPlay": false
    }
    # id = id of the match
    # status = represent the state of the game there are two RUNNING & FINISHED
    # firstPlayer = has the state of the firsplayer
        * id = UUID that represent a user in a specific match
        * pits = representation of the pits table
        * bigPit = represent the big pit of the player
        * canPlay = give the flag if the player can make a move, this change btween turns but depends of the rules can have extra movements this not change

3.- Once the match has started, it will return the necessary resources to run the game, these data are:
    Link to make the moves: http://localhost:8080/swagger-ui/#/match-controller/makeMoveUsingPUT
    
    id : is a Long that identifies a particular game between two Players.
    idPlayer : UUID it is obtained at the moment of starting the match (the player concept is given to a user participating in a match, for this reason a player has his own particular id, which positions him in a unique context of a match).
    from : This field is an integer that allows you to indicate from which pit the stones will be taken to make a move.

As a result, the match is obtained in different states of the game, which is why there are unit tests that allow evaluating each of the business rules that are the rules of the game itself.

    Example of Unit Tests
    #This test represent when a player wins an extra move, so is very easy understand how to make tests, you only need to make a new match and then moake the move and check how the pits are updating his stones afeter that move    
    
    @Test
    public void aPlayerShouldntWinAnExtraMove(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        match.makeMove(match.getFirstPlayer(),5);

        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(6,match.getSecondPlayer().getPits().getLittlePits()[5]);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(false,match.getFirstPlayer().isCanPlay());
        Assert.assertEquals(true,match.getSecondPlayer().isCanPlay());
    }

If you want to obtain information about the persistence of the objects, you can do it in the following link while the application is running.

http://localhost:8080/h2-console

las siguientes opciones deben coincidir en el panel:

    Saved Settings:	Generic H2 (Embeded)
    Setting Name: Generic H2 (Embeded)
    Driver Class: org.h2.Driver
    JDBC URL: jdbc:h2:mem:mancala
    User Name: bolcom
    Password: sa
