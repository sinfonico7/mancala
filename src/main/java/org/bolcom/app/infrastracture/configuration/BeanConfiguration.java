package org.bolcom.app.infrastracture.configuration;

import lombok.AllArgsConstructor;
import org.bolcom.app.domain.repositories.UserRepository;
import org.bolcom.app.domain.services.DomainUserService;
import org.bolcom.app.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;

    @Bean
    UserService userService(){
        return new DomainUserService(userRepository);
    }
}
