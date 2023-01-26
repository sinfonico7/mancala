package org.bolcom.app.infrastracture.entities;

import org.bolcom.app.domain.models.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {
    private String email;

    public UserEntity(String email) {
        this.email = email;
    }

    public UserEntity() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    public String getEmail() {
        return email;
    }

    public User toUser(){
        return new User(email);
    }
}
