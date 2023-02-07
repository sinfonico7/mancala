package org.bolcom.app.domain.repositories;

import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;

import java.util.Optional;
import java.util.UUID;

public interface MatchRepository {
    public Optional<Match> getMatchById(Long id);

    public Match createMatch(Match match);

    Match makeMove(Player player, int from, Long idMatch);


}
