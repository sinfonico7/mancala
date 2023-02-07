package org.bolcom.app.domain.services;

import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.repositories.PlayerRespository;
import java.util.UUID;

public class DomainPlayerService implements PlayerService {

    PlayerRespository playerRespository;
    public DomainPlayerService(PlayerRespository playerRespository){
        this.playerRespository = playerRespository;
    }

    @Override
    public Player getPlayerById(UUID id) {
        return playerRespository.getPlayerById(id);
    }
}
