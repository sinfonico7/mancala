package org.bolcom.app.infrastracture.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    public String getEmail() {
        return email;
    }
}
