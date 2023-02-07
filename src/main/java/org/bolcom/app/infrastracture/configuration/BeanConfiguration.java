package org.bolcom.app.infrastracture.configuration;

import lombok.AllArgsConstructor;
import org.bolcom.app.domain.repositories.MatchRepository;
import org.bolcom.app.domain.repositories.PlayerRespository;
import org.bolcom.app.domain.repositories.UserRepository;
import org.bolcom.app.domain.services.DomainMatchService;
import org.bolcom.app.domain.services.DomainPlayerService;
import org.bolcom.app.domain.services.DomainUserService;
import org.bolcom.app.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    private final PlayerRespository playerRespository;

    @Bean
    UserService userService(){
        return new DomainUserService(userRepository);
    }

   @Bean
    DomainMatchService matchService() {
       return new DomainMatchService(userRepository,matchRepository);
   }

   @Bean
    DomainPlayerService playerService(){return new DomainPlayerService(playerRespository);}
}
