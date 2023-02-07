package org.bolcom.app.infrastracture.repositories;

import org.bolcom.app.domain.enums.MatchStatus;
import org.bolcom.app.domain.exceptions.MatchException;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.repositories.MatchRepository;
import org.bolcom.app.infrastracture.entities.MatchEntity;
import org.bolcom.app.infrastracture.entities.PitsEntity;
import org.bolcom.app.infrastracture.entities.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MatchRepositoryImpl implements MatchRepository{


    private final JPAMatchRepository matchRepository;


    @Autowired
    public MatchRepositoryImpl(JPAMatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    @Override
    public Optional<Match> getMatchById(Long id) {
        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
        if(!matchEntity.isPresent()){
            throw new MatchException("The Match has not found",404);
        }
        return Optional.of(matchEntity.get().toMatch());
    }

    @Override
    public Match createMatch(Match match) {
       MatchEntity matchEntity = new MatchEntity();
       PlayerEntity firstPlayer = new PlayerEntity(matchEntity,null,match.getFirstPlayer().isCanPlay(),match.getFirstPlayer().getUser().getEmail().toString(),UUID.randomUUID(),match.getFirstPlayer().isFirst());
       PitsEntity pitsFirstPlayer = new PitsEntity(null,firstPlayer,6,6,6,6,6,6,0);
       firstPlayer.setPits(pitsFirstPlayer);
       PlayerEntity secondPlayer = new PlayerEntity(matchEntity,null,match.getSecondPlayer().isCanPlay(),match.getSecondPlayer().getUser().getEmail().toString(),UUID.randomUUID(),match.getSecondPlayer().isFirst());
       PitsEntity pitsScondPlayer = new PitsEntity(null,secondPlayer,6,6,6,6,6,6,0);
       secondPlayer.setPits(pitsScondPlayer);
       List<PlayerEntity> players = new ArrayList<>();
       players.add(firstPlayer);
       players.add(secondPlayer);
       matchEntity.setPlayers(players);
        return matchRepository.save(matchEntity).toMatch();
    }



    @Override
    public Match makeMove(Player player, int from,Long uuidMatch) {
        Optional<MatchEntity> matchEntity = matchRepository.findById(uuidMatch);
        if(!matchEntity.isPresent()) throw new MatchException("The match was not found",404);
        Match match = matchEntity.get().toMatch();
        Match matchUpdated = match.makeMove(player,from);
        matchEntity.get().setStatus(matchUpdated.getStatus());
        List<PlayerEntity> players = matchEntity.get().getPlayers();
        PlayerEntity first = PlayerEntity.getPlayerFirst(players);
        first.setMatch(matchEntity.get());
        first.setPits(PitsEntity.from(matchUpdated.getFirstPlayer().getPits()));
        first.setCanPLay(matchUpdated.getFirstPlayer().isCanPlay());

        PlayerEntity second = PlayerEntity.getPlayerSecond(players);
        second.setMatch(matchEntity.get());
        second.setPits(PitsEntity.from(matchUpdated.getSecondPlayer().getPits()));
        second.setCanPLay(matchUpdated.getSecondPlayer().isCanPlay());


        matchEntity.get().setPlayers(players);
        MatchEntity me = matchRepository.save(matchEntity.get());

        return new Match(first.toPlayer(),second.toPlayer(), me.getStatus(),match.getUuid());
    }



}
