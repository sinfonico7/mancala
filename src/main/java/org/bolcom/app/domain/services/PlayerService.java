package org.bolcom.app.domain.services;

import org.bolcom.app.domain.models.Player;

import java.util.UUID;

public interface PlayerService {
    Player getPlayerById(UUID id);
}
