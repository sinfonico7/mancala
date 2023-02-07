package org.bolcom.app.infrastracture.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.valueobjects.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
public class UserEntity {
    private String email;

    public UserEntity(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    public String getEmail() {
        return email;
    }

    public User toUser(){
        return new User(new Email(email));
    }
}
