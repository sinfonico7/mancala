package org.bolcom.app.player.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PLAYERS")
@Data
public class PlayerEntity {

    @Id
    @Column(name = "id_player")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlayer;

    @Column(name = "points")
    private Integer points;
}
