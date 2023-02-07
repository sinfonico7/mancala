package org.bolcom.app.domain.services;

import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.models.User;

import java.util.UUID;

public interface MatchService {

    Match createMatch(User firstPlayerEmail, User secondPlayerEmail);

    Match makeMove(Player player, int from, Long uuid);

    Match getMatch(Long uuid);
}
