package org.bolcom.app.player.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class Player {

    private String  playerName;
    @JsonIgnore
    private Integer points;

    private boolean ableToMove;

    public abstract int pickup(Integer[] table, int potPosition);

    public abstract int put(int stones, int fromPotIndex, Integer[] pots);

    public abstract void putStonesOnBigPot(int stealStones,Integer[] table);
}
