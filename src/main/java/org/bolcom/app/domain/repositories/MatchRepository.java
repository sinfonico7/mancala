package org.bolcom.app.domain.repositories;

import org.bolcom.app.domain.models.Match;

public interface MatchRepository {
    public Match getMatchById(Long id);
}
