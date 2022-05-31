package org.bolcom.app.game.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bolcom.app.game.domain.model.MancalaGameResponse;
import org.bolcom.app.game.domain.model.MovementRequest;
import org.bolcom.app.player.model.MancalaPlayer;
import org.bolcom.app.player.model.Player;
import org.bolcom.app.rules.domain.adapters.Rules;
import org.bolcom.app.rules.domain.exceptions.RuleException;
import org.springframework.http.HttpStatus;
import java.util.*;


@Data
@AllArgsConstructor
public class MancalaGameUseCase implements Rules {

    public static final int PLAYER_A_INDEX_START = 0;
    public static final int PLAYER_B_INDEX_START = 7;
    private Hashtable<String,MancalaPlayer> players;
    private Integer[] table;

    public MancalaGameUseCase(Integer[] table){
        this.table = table;
        this.players = new Hashtable<>();
    }

    @Override
    public MancalaGameResponse makeMove(MovementRequest request) {

        MancalaPlayer player = players.get(request.getPlayerName());

        if(!player.isAbleToMove()) throw new RuleException("The user is not able to move this time", HttpStatus.CONFLICT);

        if(!isTheOwnerArea(player,request.getSelectedPotIndex())) throw new RuleException("The user cannot to move stones from Opponent Area", HttpStatus.CONFLICT);

        int stones = player.pickup(table,request.getSelectedPotIndex());

        if(stones==0)throw new RuleException("Wrong Pot selected (is empty)", HttpStatus.CONFLICT);

        int lastStoneWherePutOn = player.put(stones,request.getSelectedPotIndex() + 1,table);

        if(wasPutOnABigPot(player,lastStoneWherePutOn)){
            player.setAbleToMove(true);
        }

        if(!wasPutOnABigPot(player,lastStoneWherePutOn) && isTheOwnerArea(player,lastStoneWherePutOn) && canStealStones(lastStoneWherePutOn)){
            int stealStones = stealStonesOnOppositeSide(lastStoneWherePutOn);
            player.putStonesOnBigPot(stealStones,table);
            player.setAbleToMove(true);
        }

        return checkHwoPlayNext(player);
    }

    private MancalaGameResponse checkHwoPlayNext(MancalaPlayer player) {
        MancalaGameResponse mancalaGameResponse = MancalaGameResponse.builder()
                .state("IN_PROGRESS")
                .build();
        if(player.isAbleToMove()==false){
            giveTurnToOpponent(player);
        }
        if(noMoreStonesToMove(player)){
            stopGame();
            mancalaGameResponse.setState("FINISHED");
        }
        mancalaGameResponse.setPlayers(new ArrayList<>(players.values()));
        mancalaGameResponse.setTable(table);
        return mancalaGameResponse;
    }

    private boolean wasPutOnABigPot(MancalaPlayer player, int lastStoneWherePutOn) {
        return (player.getStartIndexTable()+6 == lastStoneWherePutOn);
    }

    private boolean canStealStones(int lastStoneWherePutOn) {
        return table[lastStoneWherePutOn] == 1 && (table[getOppositePotIndex(lastStoneWherePutOn)] != 0);
    }



    private void giveTurnToOpponent(MancalaPlayer player) {

            Enumeration<String> enumeration = players.keys();
            while (enumeration.hasMoreElements()){
                String key = enumeration.nextElement();
                if(!key.equals(player.getPlayerName())){
                    players.get(key).setAbleToMove(true);
                }
            }

    }

    @Override
    public Player getWinner(List<Player> players) {
        return players.stream().max(Comparator.comparing(Player::getPoints)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void stopGame() {
        List<Integer> playerPots = Arrays.asList(table);
        int playerABigpot = PLAYER_A_INDEX_START +6;
        int playerBBigpot = PLAYER_B_INDEX_START +6;
        table[playerABigpot] += playerPots.subList(PLAYER_A_INDEX_START,playerABigpot).stream().mapToInt(o -> o.intValue()).sum();
        table[playerBBigpot] += playerPots.subList(PLAYER_B_INDEX_START,playerBBigpot).stream().mapToInt(o -> o.intValue()).sum();

    }

    @Override
    public MancalaGameResponse initGame(List<MancalaPlayer> players) {

        if(players.size()!=2) throw new RuleException("The number of players must be exactly 2", HttpStatus.CONFLICT);
        MancalaPlayer playerA = players.get(0);
        playerA.setStartIndexTable(PLAYER_A_INDEX_START);
        playerA.setAbleToMove(true);
        this.players.put(playerA.getPlayerName(),playerA);
        MancalaPlayer playerB = players.get(1);
        playerB.setStartIndexTable(PLAYER_B_INDEX_START);
        playerB.setAbleToMove(false);
        this.players.put(playerB.getPlayerName(),playerB);
        return MancalaGameResponse.builder()
                .players(new ArrayList<>(this.players.values()))
                .table(table)
                .state("IN_PROGRESS")
                .build();
    }

    private int stealStonesOnOppositeSide(int currentPosition){
        int stones = table[getOppositePotIndex(currentPosition)];
        table[currentPosition] = 0;
        table[getOppositePotIndex(currentPosition)] = 0;
        return stones + 1;
    }


    private int getOppositePotIndex(int currentPosition){
        return (13 - currentPosition);
    }

    private boolean noMoreStonesToMove(MancalaPlayer player){
        final int AREA_POTS_LENGTH = 6;
        int playerAreaAStartIndex = player.getStartIndexTable();
        List<Integer> playerPots = Arrays.asList(table);
        int qtyOfStones = playerPots.subList(playerAreaAStartIndex,playerAreaAStartIndex+AREA_POTS_LENGTH).stream().mapToInt(o -> o.intValue()).sum();
        return qtyOfStones == 0;
    }

    private boolean isTheOwnerArea(MancalaPlayer player, int wherePretendPickUp){
        int minimum = player.getStartIndexTable();
        int maximum = minimum + 5;
        return (wherePretendPickUp <= maximum) && (wherePretendPickUp >= minimum);
    }

}
