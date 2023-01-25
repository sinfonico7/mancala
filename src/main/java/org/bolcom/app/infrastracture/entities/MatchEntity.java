package org.bolcom.app.infrastracture.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class MatchEntity {
    private Long id;
    private UserEntity userOne;
    private UserEntity userTwo;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }


}
