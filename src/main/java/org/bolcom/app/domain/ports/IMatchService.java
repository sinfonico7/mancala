package org.bolcom.app.domain.adapters;

import org.bolcom.app.domain.models.Match;

public interface IMatchService {

    Match getMatch(String firstPlayerEmail,String secondPlayerEmail);
}
