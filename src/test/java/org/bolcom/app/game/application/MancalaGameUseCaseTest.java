package org.bolcom.app.game.application;

import org.bolcom.app.game.domain.model.MovementRequest;
import org.bolcom.app.player.model.MancalaPlayer;
import org.bolcom.app.player.model.Player;
import org.bolcom.app.rules.domain.exceptions.RuleException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class MancalaGameUseCaseTest {

    public static final int PLAYER_A_INDEX_START = 0;
    public static final int PLAYER_B_INDEX_START = 7;

    @Test
    public void makeMove() {
        MancalaPlayer player = new MancalaPlayer();
        player.setAbleToMove(false);
        MancalaPlayer player2 = new MancalaPlayer();
        Hashtable<String,MancalaPlayer> players = new Hashtable<>();
        players.put("PlayerA",player);
        players.put("PlayerB",player);

        Integer[] dummyTable = new Integer[]{};
        MancalaGameUseCase mancalaGameUseCase = new MancalaGameUseCase(players,dummyTable);
        assertThrows(RuleException.class, () -> mancalaGameUseCase.makeMove(MovementRequest
                .builder()
                .playerName("PlayerA")
                .selectedPotIndex(0)
                .build()));

    }

    @Test
    public void firstMakeMoveButThenCannot() {
        MancalaPlayer player = new MancalaPlayer();
        player.setAbleToMove(true);
        MancalaPlayer player2 = new MancalaPlayer();
        Hashtable<String,MancalaPlayer> players = new Hashtable<>();
        players.put("PlayerA",player);
        players.put("PlayerB",player);

        MovementRequest movement = MovementRequest
                .builder()
                .playerName("PlayerA")
                .selectedPotIndex(5)
                .build();
        Integer[] table = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        MancalaGameUseCase mancalaGameUseCase = new MancalaGameUseCase(players,table);
        mancalaGameUseCase.makeMove(movement);
        assertThrows(RuleException.class, () -> mancalaGameUseCase.makeMove(movement));

    }

    @Test
    public void shouldStealStonesFromPlayerAToOpponent(){
        MancalaPlayer player = new MancalaPlayer();
        player.setAbleToMove(true);
        MancalaPlayer player2 = new MancalaPlayer();
        Hashtable<String,MancalaPlayer> players = new Hashtable<>();
        players.put("PlayerA",player);
        players.put("PlayerB",player);

        MovementRequest movement = MovementRequest
                .builder()
                .playerName("PlayerA")
                .selectedPotIndex(1)
                .build();
        Integer[] tableBeforeSteal = new Integer[]{6,2,6,0,6,6,0,6,6,6,6,6,6,0};
        MancalaGameUseCase mancalaGameUseCase = new MancalaGameUseCase(players,tableBeforeSteal);

        Integer[] tableAfterSteal = new Integer[]{6,0,7,0,6,6,7,6,6,0,6,6,6,0};
        assertArrayEquals(tableAfterSteal,mancalaGameUseCase.makeMove(movement).getTable());
    }

    @Test
    public void shouldStealStonesFromPlayerBToOpponent(){
        MancalaPlayer player = new MancalaPlayer();
        player.setStartIndexTable(PLAYER_A_INDEX_START);
        player.setAbleToMove(false);
        MancalaPlayer player2 = new MancalaPlayer();
        player2.setStartIndexTable(PLAYER_B_INDEX_START);
        player2.setAbleToMove(true);
        Hashtable<String,MancalaPlayer> players = new Hashtable<>();
        players.put("PlayerA",player);
        players.put("PlayerB",player2);

        MovementRequest movement = MovementRequest
                .builder()
                .playerName("PlayerB")
                .selectedPotIndex(8)
                .build();
        Integer[] tableBeforeSteal = new Integer[]{6,6,6,6,6,6,0,6,2,6,0,6,6,0};
        MancalaGameUseCase mancalaGameUseCase = new MancalaGameUseCase(players,tableBeforeSteal);

        Integer[] tableAfterSteal =  new Integer[]{6,6,0,6,6,6,0,6,0,7,0,6,6,7};
        assertArrayEquals(mancalaGameUseCase.makeMove(movement).getTable(),tableAfterSteal);
        assertTrue(player2.isAbleToMove());
    }

}