package org.bolcom.app.infrastracture.web;

import org.bolcom.app.domain.adapters.IMatchService;
import org.bolcom.app.domain.models.Match;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    IMatchService iMatchService;

    public Match getMatch(String firstPlayerEmail, String secondPlayer) {
        return iMatchService.getMatch(firstPlayerEmail,secondPlayer);
    }
}
