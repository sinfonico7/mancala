package org.bolcom.app.application.requests;

import lombok.Data;
import org.bolcom.app.domain.models.Player;

import java.util.UUID;

@Data
public class MatchRequestMove{
    private UUID idPlayer;
    private int from;

}
