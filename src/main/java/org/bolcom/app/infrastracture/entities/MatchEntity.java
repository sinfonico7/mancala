package org.bolcom.app.infrastracture.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bolcom.app.domain.enums.MatchStatus;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "MATCHES")
@Data
@Builder
@AllArgsConstructor
public class MatchEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "status")
    private MatchStatus status;



    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST)
    private List<PlayerEntity> players;

 /*   @OneToOne(targetEntity = PlayerEntity.class, mappedBy = "match", cascade = {CascadeType.ALL},orphanRemoval = true)
    private PlayerEntity playerEntityOne;

    @OneToOne(targetEntity = PlayerEntity.class, mappedBy = "match", cascade = {CascadeType.ALL},orphanRemoval = true)
    private PlayerEntity playerEntityTwo;
*/

    public MatchEntity() {
        this.status = MatchStatus.RUNNING;
    }

    public Match toMatch(){
        return new Match(id,status, PlayerEntity.toPlayers(players));
    }

   public static MatchEntity toMatchEntity(Match match){
         List<Player> players = new ArrayList<>();
         players.add(match.getFirstPlayer());
         players.add(match.getSecondPlayer());
         List<PlayerEntity> playerEntities = players.stream().map(player -> PlayerEntity.from(player)).collect(Collectors.toList());

         return MatchEntity.builder()
                 .players(playerEntities)
                 .id(match.getUuid())
                 .status(match.getStatus())
                 .build();
    }
}