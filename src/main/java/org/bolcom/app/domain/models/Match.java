package org.bolcom.app.domain.models;

import org.bolcom.app.domain.enums.MatchStatus;
import org.bolcom.app.domain.exceptions.MatchException;

import java.util.List;
import java.util.stream.IntStream;

public class Match {
    private Long uuid;
    private final int LAST_PIT = 5;
    private MatchStatus status;
    private final Player firstPlayer;
    private final Player secondPlayer;

    public Match (User firstPlayerEmail, User secondPlayerEmail){
        status = MatchStatus.RUNNING;
        this.firstPlayer = new Player(firstPlayerEmail.getEmail());
        this.firstPlayer.setCanPlay(true);
        this.firstPlayer.setFirst(true);
        this.secondPlayer = new Player(secondPlayerEmail.getEmail());
        this.secondPlayer.setCanPlay(false);
    }

    public Match(Long uuid, MatchStatus status, List<Player> players) {
        this.uuid = uuid;
        this.status = status;
        this.firstPlayer = getPlayerFirst(players);
        this.secondPlayer =  getPlayerSecond(players);
    }

    public Match(Player firstPlayer,Player secondPlayer,MatchStatus status,Long uuid){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.status = status;
        this.uuid = uuid;
    }

    public Player getPlayerFirst(List<Player> players) {
        return players.stream().filter(Player::isFirst).findAny().get();
    }

    public Player getPlayerSecond(List<Player> players) {
        return players.stream().filter(player -> player.isFirst() != true).findFirst().get();
    }

    public Match makeMove(Player player, int from){

        if(!player.isCanPlay()) throw new MatchException(String.format("[makeMove] The User %s cant play this turn",player.getUser().getEmail()),409);

        int stonesQty = player.getPits().pickStones(from);

        boolean getOponentSide = false;

        for (int i = stonesQty ; i>0; i--){
            stonesQty--;
            if(from==LAST_PIT) {
                player.getPits().putStonesOnBigPit(1);
                getOponentSide = true;
                from = 0;
                continue;
            }

            if(getOponentSide){
                getOpponentSide(player).getPits().putStones(from,1);
                from++;
            }else {
                from++;
                player.getPits().putStones(from,1);
                if(from==LAST_PIT){
                    getOponentSide = true;
                }
            }

        }
        //Rules Game Play
        checkExtraTurn(player,stonesQty,from);

        //Capture Stones?
        captureStones(player,from);
        //End Game ?
        checkEndGame();

        return new Match(getFIrstPlayerByProperty(player),getSecondPlayerByProperty(player),getStatus(),getUuid());
    }

    private Player getFIrstPlayerByProperty(Player player) {
        if(player.isFirst()){
            return player;
        }
        return getOpponentSide(player);
    }

    private Player getSecondPlayerByProperty(Player player) {
        if(!player.isFirst()){
            return player;
        }
        return getOpponentSide(player);
    }

    private void checkEndGame() {
        int firstPlayerStones = IntStream.of(firstPlayer.getPits().getLittlePits()).sum();
        int secondPlayerStones = IntStream.of(secondPlayer.getPits().getLittlePits()).sum();
        if(firstPlayerStones==0 || secondPlayerStones==0){
            status = MatchStatus.FINISHED;
            firstPlayer.setCanPlay(false);
            secondPlayer.setCanPlay(false);
        }
    }

    private void captureStones(Player player, int from) {
        int qtyStonesInLastPit = player.getPits().getLittlePits()[from];
        if(qtyStonesInLastPit==1){
            int total = player.getPits().pickStones(from) +  getOpponentSide(player).getPits().pickStones(LAST_PIT-from);
            player.getPits().putStonesOnBigPit(total);
        }
    }

    private void checkExtraTurn(Player player,int stonesQty, int from) {
        if(stonesQty==0 && from==0){
            player.setCanPlay(true);
            getOpponentSide(player).setCanPlay(false);
        }else{
            getOpponentSide(player).setCanPlay(true);
            player.setCanPlay(false);
        }
    }

    public Player getOpponentSide(Player player){
        return player.getUser().getEmail()==firstPlayer.getUser().getEmail() ? secondPlayer : firstPlayer;
    }

    public Player getFirstPlayer(){
        return firstPlayer;
    }

    public Player getSecondPlayer(){
        return secondPlayer;
    }

    public Long getUuid(){
        return uuid;
    }
    public MatchStatus getStatus(){
        return status;
    }

    @Override
    public String toString() {
        return "Match{\r\n" +
                "firstPlayer=" + firstPlayer +
                "secondPlayer=" + secondPlayer +
                '}';
    }
}
