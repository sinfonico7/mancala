package org.bolcom.app.domain.models;
import org.bolcom.app.domain.valueobjects.Email;

public class User {
    private Email email;

    public User (Email email){
        this.email = email;
    }

    public User() {
    }

    public Email getEmail(){
        return email;
    }

    @Override
    public String toString() {
        return email.toString();
    }
}
