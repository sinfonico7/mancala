package org.bolcom.app.application.controllers;

import org.bolcom.app.application.requests.MatchRequest;
import org.bolcom.app.application.requests.MatchRequestMove;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.services.MatchService;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.services.PlayerService;
import org.bolcom.app.domain.valueobjects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/games/mancala/v1")
public class MatchController {

    private final MatchService matchService;
    private final PlayerService playerService;
    @Autowired
    public MatchController(MatchService matchService, PlayerService playerService) {
        this.matchService = matchService;
        this.playerService = playerService;
    }

    @PostMapping("/matches")
    public ResponseEntity<Match> startMatch(@RequestBody MatchRequest matchRequest) {
        User fisrtUser = new User(new Email(matchRequest.getFirstPlayerEmail()));
        User secondUser = new User(new Email(matchRequest.getSecondPlayerEmail()));
        Match match = matchService.createMatch(fisrtUser,secondUser);
        return new ResponseEntity<>(match,HttpStatus.OK);
    }

    @PutMapping("/matches/{uuid}/make-move")
    public ResponseEntity<Match> makeMove(@PathVariable Long uuid,@RequestBody MatchRequestMove requestMove){
        int from = requestMove.getFrom();
        Player player = playerService.getPlayerById(requestMove.getIdPlayer());
        return new ResponseEntity<>(matchService.makeMove(player,from,uuid),HttpStatus.OK);
    }


}
