package org.bolcom.app.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bolcom.app.domain.valueobjects.Email;

import java.util.UUID;

public class Player {

    private UUID id;
    @JsonIgnore
    private boolean isFirst;
    @JsonIgnore
    private User user;
    private Pits pits;

    private boolean canPlay;
    public Player (Email email){
        user = new User(email);
        pits = new Pits();
    }

    public Player (Email email,Pits pits,boolean canPlay,UUID id,boolean isFirst){
        user = new User(email);
        this.pits = pits;
        this.canPlay = canPlay;
        this.id = id;
        this.isFirst = isFirst;
    }

    public Pits getPits(){
        return this.pits;
    }

    public User getUser(){
        return user;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    public String toString() {
        return "{" +
                "user=" + user.getEmail() +
                String.format(", pits= [%s][%s][%s][%s][%s][%s] Big pit[%s]",
                        pits.getLittlePits()[0],
                        pits.getLittlePits()[1],
                        pits.getLittlePits()[2],
                        pits.getLittlePits()[3],
                        pits.getLittlePits()[4],
                        pits.getLittlePits()[5],pits.getBigPit()) +
                ", canPlay=" + canPlay +
                "}\r\n";
    }
}
