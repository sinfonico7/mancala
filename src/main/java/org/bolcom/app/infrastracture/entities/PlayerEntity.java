package org.bolcom.app.infrastracture.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bolcom.app.domain.models.Pits;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.valueobjects.Email;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "PLAYERS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity {


    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private MatchEntity match;

    @OneToOne(mappedBy = "playerEntity",cascade = CascadeType.ALL)
    private PitsEntity pits;

    @Column(name = "can_play")
    private boolean canPLay;

    @Column(name = "email")
    private String email;
    @Id
    @Column(name = "id_player")
    private UUID id;

    @Column(name = "is_firts")
    private boolean isFirst;

   public Player toPlayer(){
        return new Player(new Email(email),pits.toPits(),canPLay,id,isFirst);
   }

    public static List<Player> toPlayers(List<PlayerEntity> playerEntities){
        return playerEntities.stream().map(PlayerEntity::toPlayer).collect(Collectors.toList());
    }

    public static PlayerEntity from(Player player){
        return PlayerEntity.builder()
                .id(player.getId())
                .canPLay(player.isCanPlay())
                .isFirst(player.isFirst())
                .email(player.getUser().getEmail().toString())
                .pits(PitsEntity.from(player.getPits()))
                .build();
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setPits(PitsEntity pits) {
        this.pits = pits;
    }

    public static PlayerEntity getPlayerFirst(List<PlayerEntity> players) {
        return players.stream().filter(PlayerEntity::isFirst).findAny().get();
    }

    public static PlayerEntity getPlayerSecond(List<PlayerEntity> players) {
        return players.stream().filter(player -> player.isFirst() != true).findFirst().get();
    }
}
