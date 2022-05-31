package org.bolcom.app.player.model;

import org.bolcom.app.game.application.MancalaGameUseCase;
import org.bolcom.app.game.domain.model.MovementRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class MancalaPlayerTest {

    MancalaGameUseCase mancalaGameUseCase;
    Hashtable<String,MancalaPlayer> players;
    private final int PLAYER_A_INDEX_START = 0;
    private final int PLAYER_B_INDEX_START = 7;
    MancalaPlayer playerA;
    MancalaPlayer playerB;

    @Before
    public void setUp() {
        players = new Hashtable<>();
        playerA = new MancalaPlayer(PLAYER_A_INDEX_START);
        playerB = new MancalaPlayer(PLAYER_B_INDEX_START);
        players.put("PlayerA",playerA);
        players.put("PlayerB",playerB);
    }

    @Test
    public void whenPlayerTooksTheStonesFromAPotThePotGetsEmpty(){
        Integer[]  tableAfterPick = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        mancalaGameUseCase = new MancalaGameUseCase(players,tableAfterPick);
        playerA.pickup(tableAfterPick,5);
        assertEquals(tableAfterPick[5].intValue(),0);
    }

    @Test
    public void shouldPlayerAPutCorrectlyTheStones(){
        MovementRequest movementRequest =MovementRequest
                .builder()
                .playerName("PlayerA")
                .selectedPotIndex(5)
                .build();
        Integer[]  tableBeforeMove = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        mancalaGameUseCase = new MancalaGameUseCase(players,tableBeforeMove);
        Integer [] tableAfterMovement = new Integer[]{6,6,6,6,6,0,1,7,7,7,7,7,6,0};
        playerA.setAbleToMove(true);
        mancalaGameUseCase.makeMove(movementRequest);
        assertArrayEquals(tableBeforeMove,tableAfterMovement);
    }

    @Test
    public void playerAShouldntPutAnyStonesOnOpponentBigPot(){
        MovementRequest movementRequest =MovementRequest
                .builder()
                .playerName("PlayerA")
                .selectedPotIndex(13)
                .build();
        Integer[]  tableAfterPut = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        mancalaGameUseCase = new MancalaGameUseCase(players,tableAfterPut);
        playerA.put(1,13,tableAfterPut);
        assertEquals(tableAfterPut[13].intValue(),0);
        Integer[]  tableBeforePut = new Integer[]{7,6,6,6,6,6,0,6,6,6,6,6,6,0};
        assertArrayEquals(tableAfterPut,tableBeforePut);
    }

    @Test
    public void playerBShouldntPutAnyStonesOnOpponentBigPot(){

        Integer[]  tableAfterPut = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        mancalaGameUseCase = new MancalaGameUseCase(players,tableAfterPut);
        playerB.put(1,6,tableAfterPut);
        assertEquals(tableAfterPut[6].intValue(),0);
        Integer[]  tableBeforePut = new Integer[]{6,6,6,6,6,6,0,7,6,6,6,6,6,0};
        assertArrayEquals(tableAfterPut,tableBeforePut);
    }


}