package org.bolcom.app.domain.models;

public class Match {
    private final int LAST_PIT = 5;
    private final int FIRST_PIT = 0;
    private Player firstPlayer;
    private Player secondPlayer;

    public Match (String firstPlayerEmail, String secondPlayerEmail){
        this.firstPlayer = new Player(firstPlayerEmail);
        this.secondPlayer = new Player(secondPlayerEmail);
    }

    public void makeMove(Player player, int from){
        int stonesQty = player.getPits().pickStones(from);
        // table is counting from 1 to 6 the big pit is 7
        boolean getOponentSide = false;
        --from;
        for (int i = stonesQty ; i>0; i--){

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
}
