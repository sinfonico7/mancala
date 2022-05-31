package org.bolcom.app.game.domain.configuration.usecases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.bolcom.app.game.application.MancalaGameUseCase;
import org.bolcom.app.player.model.MancalaPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;

@Configuration
@AllArgsConstructor
public class MancalaGameUseCaseConfiguration {

    /*
    * Player B => [7->13] Big pot Player B -> 13
    * Player A => [0->6]  Big pot Player A -> 6
    */
    @Bean
    public MancalaGameUseCase setUpMancalaClassicGame(){
        Integer[] table = new Integer[]{6,6,6,6,6,6,0,6,6,6,6,6,6,0};
        return new MancalaGameUseCase(table);
    }



}
