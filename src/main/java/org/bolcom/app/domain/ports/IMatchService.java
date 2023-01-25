package org.bolcom.app.domain.ports;

import org.bolcom.app.domain.models.Match;

public interface IMatchService {

    Match getMatch(String firstPlayerEmail,String secondPlayerEmail);
}
