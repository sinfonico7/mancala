package org.bolcom.app.models;

import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MatchTests {

    private static Player firstPlayer;
    private static Player secondPlayer;

    @BeforeAll
    public static void init(){
        firstPlayer = new Player("firstPlayer@nerdapps.com");
        secondPlayer = new Player("secondPlayer@nerdapps.com");
    }


    @Test
    public void shouldReturnOppsitePlayer(){
        Match match = new Match("firstPlayer@nerdapps.com","secondPlayer@nerdapps.com");
        Assert.assertNotEquals(match.getFirstPlayer(),match.getOpponentSide(match.getFirstPlayer()));
        Assert.assertEquals(match.getOpponentSide(match.getFirstPlayer()).getUser().getEmail(),match.getSecondPlayer().getUser().getEmail());

    }

    @Test
    public void secondPlayerHasToGetOnePlusStoneAfterPlayerOneMove(){
        Match match = new Match("firstPlayer@nerdapps.com","secondPlayer@nerdapps.com");
        match.makeMove(match.getFirstPlayer(),6);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getPits()[0]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getPits()[1]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getPits()[2]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getPits()[3]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getPits()[4]);
        Assert.assertEquals(6,match.getSecondPlayer().getPits().getPits()[5]);

    }

    @Test
    public void firstPlayerMakesMoveFromBeginning(){
        Match match = new Match("firstPlayer@nerdapps.com","secondPlayer@nerdapps.com");
        match.makeMove(match.getFirstPlayer(),1);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(0,match.getFirstPlayer().getPits().getPits()[0]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getPits()[1]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getPits()[2]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getPits()[3]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getPits()[4]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getPits()[5]);

    }
}
