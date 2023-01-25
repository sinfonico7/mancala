package org.bolcom.app.domain.models;

public class Player {

    private User user;
    private Pits pits;

    public Player (String email){
        user = new User(email);
        pits = new Pits();
    }

    public Pits getPits(){
        return this.pits;
    }

    public User getUser(){
        return user;
    }


}
