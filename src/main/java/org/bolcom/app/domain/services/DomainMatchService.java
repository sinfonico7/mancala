package org.bolcom.app.domain.services;
import org.bolcom.app.domain.exceptions.UserException;
import org.bolcom.app.domain.models.Match;
import org.bolcom.app.domain.models.Player;
import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.repositories.MatchRepository;
import org.bolcom.app.domain.repositories.UserRepository;
import java.util.Optional;

public class DomainMatchService implements MatchService{

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public DomainMatchService(UserRepository userRepository, MatchRepository matchRepository){
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public Match createMatch(User firstPlayerEmail, User secondPlayerEmail) {
        Optional<User> firstUser = userRepository.findById(firstPlayerEmail.getEmail().toString());
        Optional<User> secodUser = userRepository.findById(secondPlayerEmail.getEmail().toString());
        if(!firstUser.isPresent() || !secodUser.isPresent()){
            throw new UserException("[MatchService] Some user doesnt exist [getMatch]",404);
        }
        Match match = new Match(firstUser.get(),secodUser.get());
        return matchRepository.createMatch(match);
    }

    @Override
    public Match makeMove(Player player, int from, Long uuid) {
        return matchRepository.makeMove(player,from, uuid);
    }

    @Override
    public Match getMatch(Long uuid) {
        Optional<Match> match = matchRepository.getMatchById(uuid);
        return match.get();
    }
}
