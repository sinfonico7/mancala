package org.bolcom.app.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Hashtable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MancalaPlayer extends Player {

    @JsonIgnore
    private int startIndexTable;

    @Override
    public int pickup(Integer[] pots,int pot) {
        int stones = pots[pot];
        pots[pot] = 0;
        return stones;
    }

    @Override
    public int put(int stones, int indexFrom ,Integer[] pots) {
        int lastPotWherePut = 0;
        for (int i = indexFrom ; stones != 0 ; i++){
            if(bigPotOpponentIndex() != i){
                pots[i] = pots[i] +1;
                stones--;
            }else{
                i = startIndexTable -1;
            }
            if(i==pots.length-1){
                i = -1;
                lastPotWherePut = pots.length-1;
            }else{
                lastPotWherePut = i;
            }

        }
        this.setAbleToMove(false);
        return lastPotWherePut;
        // where is the las index that we put the stone? is only one stone in that pot? how i know what is my opposite pot? ex:
    }

    @Override
    public void putStonesOnBigPot(int stealStones, Integer[] table) {
        table[getBigPotIndex()] = table[getBigPotIndex()] + stealStones;
    }

    private int getBigPotIndex(){
        return startIndexTable + 6;
    }

    private int bigPotOpponentIndex(){
        return startIndexTable == 0 ? 13 : 6;
    }

}
