package org.bolcom.app.domain.valueobjects;

import org.bolcom.app.domain.exceptions.ValidationException;

public class Email {

    private String user;
    private String domain;

    public Email (String email){
        if(email==null || !email.contains("@")) throw new ValidationException("Bad email format, must contain @",400);
        String [] data = email.split("@");
        String user = data[0];
        String domain = data[1];
        if(user.isEmpty() || domain.isEmpty()) throw new ValidationException("Bad email format, user or domain cant be empty ",400);
        this.user = user;
        this.domain = domain;
    }

    public Email() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return String.format("%s@%s",user,domain);
    }
}
