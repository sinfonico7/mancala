package org.bolcom.app.models;

import org.bolcom.app.domain.exceptions.MatchException;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.valueobjects.Email;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MatchTests {

    private static Player firstPlayer;
    private static Player secondPlayer;

    @BeforeAll
    public static void init(){
        firstPlayer = new Player(new Email("firstPlayer@nerdapps.com"));
        secondPlayer = new Player(new Email("secondPlayer@nerdapps.com"));
    }


    @Test
    public void shouldReturnOppsitePlayer(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        Assert.assertNotEquals(match.getFirstPlayer(),match.getOpponentSide(match.getFirstPlayer()));
        Assert.assertEquals(match.getOpponentSide(match.getFirstPlayer()).getUser().getEmail(),match.getSecondPlayer().getUser().getEmail());

    }

    @Test
    public void secondPlayerHasToGetOnePlusStoneAfterPlayerOneMove(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        match.makeMove(match.getFirstPlayer(),5);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(6,match.getSecondPlayer().getPits().getLittlePits()[5]);

    }

    @Test
    public void firstPlayerMakesMoveFromBeginning(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        match.makeMove(match.getFirstPlayer(),0);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
            Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[5]);

    }

    @Test
    public void aPlayerShouldWinAnExtraMove(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        match.makeMove(match.getFirstPlayer(),0);

        Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[5]);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(true,match.getFirstPlayer().isCanPlay());
        Assert.assertEquals(false,match.getSecondPlayer().isCanPlay());
    }

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

    @Test
    public void playerShouldCaptureOppositePit(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        match.makeMove(match.getFirstPlayer(),0);

        Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(7,match.getFirstPlayer().getPits().getLittlePits()[5]);
        Assert.assertEquals(1,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(true,match.getFirstPlayer().isCanPlay());
        Assert.assertEquals(false,match.getSecondPlayer().isCanPlay());
        //Second move
        match.makeMove(match.getFirstPlayer(),1);
        Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(8,match.getFirstPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(8,match.getFirstPlayer().getPits().getLittlePits()[3]);
        Assert.assertEquals(8,match.getFirstPlayer().getPits().getLittlePits()[4]);
        Assert.assertEquals(8,match.getFirstPlayer().getPits().getLittlePits()[5]);
        Assert.assertEquals(2,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[0]);
        Assert.assertEquals(7,match.getSecondPlayer().getPits().getLittlePits()[1]);
        Assert.assertEquals(false,match.getFirstPlayer().isCanPlay());
        Assert.assertEquals(true,match.getSecondPlayer().isCanPlay());
        System.out.println(match);
        match.makeMove(match.getSecondPlayer(),3);
        System.out.println(match);
        match.makeMove(match.getFirstPlayer(),2);
        System.out.println(match);
        match.makeMove(match.getSecondPlayer(),0);
        System.out.println(match);
        //Must Capture
        match.makeMove(match.getFirstPlayer(),0);
        System.out.println(match);
        Assert.assertEquals(0,match.getFirstPlayer().getPits().getLittlePits()[2]);
        Assert.assertEquals(6,match.getFirstPlayer().getPits().getBigPit());
        Assert.assertEquals(0,match.getSecondPlayer().getPits().getLittlePits()[3]);
        System.out.println(match);
    }

    @Test
    public void thePlayerShouldntMakeMove(){
        Match match = new Match(new User(new Email("firstPlayer@nerdapps.com")),new User(new Email("secondPlayer@nerdapps.com")));
        Assert.assertThrows(MatchException.class,() -> match.makeMove(match.getSecondPlayer(),0));
    }

}
