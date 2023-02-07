package org.bolcom.app.domain.repositories;

import org.bolcom.app.domain.models.Player;

import java.util.UUID;

public interface PlayerRespository {

    Player getPlayerById(UUID id);
}
