package org.bolcom.app.game.infraestructure;

import lombok.RequiredArgsConstructor;
import org.bolcom.app.game.domain.model.MancalaGameResponse;
import org.bolcom.app.game.domain.model.MovementRequest;
import org.bolcom.app.player.model.MancalaPlayer;
import org.bolcom.app.rules.domain.adapters.Rules;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/games/mancala")
public class MancalaGameController {

    private final Rules mancalaGameUseCase;

    @PostMapping(value="/startGame", produces = { "application/json" } )
    public MancalaGameResponse startGame(@RequestBody List<MancalaPlayer> players){
        return mancalaGameUseCase.initGame(players);
    }

    @PatchMapping(value = "/MakeMove")
    public MancalaGameResponse makeMove(@RequestBody MovementRequest request){
        return mancalaGameUseCase.makeMove(request);
    }
    
}
