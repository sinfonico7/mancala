package org.bolcom.app.application.services;

import org.bolcom.app.domain.adapters.IMatchService;
import org.bolcom.app.domain.exceptions.UserException;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.infrastracture.entities.UserEntity;
import org.bolcom.app.infrastracture.repositories.JPAMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService implements IMatchService {

    @Autowired
    private JPAMatchRepository repository;
    @Override
    public Match getMatch(String firstPlayerEmail,String secondPlayerEmail) {
        Optional<UserEntity> firstUser = repository.findById(firstPlayerEmail);
        Optional<UserEntity> secodUser = repository.findById(secondPlayerEmail);
        if(!firstUser.isPresent() || !secodUser.isPresent()){
            throw new UserException("[MatchService] Some user doesnt exist [getMatch]",401);
        }

        return new Match(firstUser.get().getEmail(),secodUser.get().getEmail());
    }
}
