package org.bolcom.app.infrastracture.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MATCHES")
public class MatchEntity implements Serializable {

    private Long id;

    @Column(name = "id_user_one")
    private String idUserOne;
    @Column(name = "id_user_two")
    private String idUserTwo;
    @Column(name = "points_user_one")
    private int pointsUserOne;
    @Column(name = "points_user_two")
    private int pointsUserTwo;
    @Column(name = "status")
    private Enum status;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getIdUserOne() {
        return idUserOne;
    }

    public String getIdUserTwo() {
        return idUserTwo;
    }

    public int getPointsUserOne() {
        return pointsUserOne;
    }

    public int getPointsUserTwo() {
        return pointsUserTwo;
    }

    public Enum getStatus() {
        return status;
    }

    public void setIdUserOne(String idUserOne) {
        this.idUserOne = idUserOne;
    }

    public void setIdUserTwo(String idUserTwo) {
        this.idUserTwo = idUserTwo;
    }

    public void setPointsUserOne(int pointsUserOne) {
        this.pointsUserOne = pointsUserOne;
    }

    public void setPointsUserTwo(int pointsUserTwo) {
        this.pointsUserTwo = pointsUserTwo;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }
}
