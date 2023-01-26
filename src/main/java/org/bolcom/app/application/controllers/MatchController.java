package org.bolcom.app.application.controllers;

import org.bolcom.app.domain.ports.IMatchService;
import org.bolcom.app.domain.models.Match;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    IMatchService iMatchService;

    public Match getMatch(String firstPlayerEmail, String secondPlayer) {
        return iMatchService.getMatch(firstPlayerEmail,secondPlayer);
    }
}
