package org.bolcom.app.infrastracture.repositories;

import org.bolcom.app.domain.exceptions.MatchException;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.repositories.PlayerRespository;
import org.bolcom.app.infrastracture.entities.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public class PlayerRepositoryImpl implements PlayerRespository {

    JPAPlayerRerpository playerRerpository;

    @Autowired
    public PlayerRepositoryImpl(JPAPlayerRerpository playerRerpository) {
        this.playerRerpository = playerRerpository;
    }

    @Override
    public Player getPlayerById(UUID id) {
        Optional<PlayerEntity> playerEntity  = playerRerpository.findById(id);
        if(!playerEntity.isPresent()){
            throw new MatchException("The player was not found",404);
        }
        return playerEntity.get().toPlayer();
    }
}
