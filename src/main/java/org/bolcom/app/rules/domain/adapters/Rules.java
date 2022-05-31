package org.bolcom.app.rules.domain.adapters;

import org.bolcom.app.game.domain.model.MancalaGameResponse;
import org.bolcom.app.game.domain.model.MovementRequest;
import org.bolcom.app.player.model.MancalaPlayer;
import org.bolcom.app.player.model.Player;

import java.util.List;

public interface Rules {

    MancalaGameResponse makeMove(MovementRequest movementRequest);

    Player getWinner(List<Player> players);

    void stopGame();

    MancalaGameResponse initGame(List<MancalaPlayer> players);
}
