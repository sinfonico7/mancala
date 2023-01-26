package org.bolcom.app.infrastracture.repositories;

import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.repositories.UserRepository;
import org.bolcom.app.infrastracture.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class H2Repository implements UserRepository {

    private final JPAUserRepository jpaUserRepository;

    @Autowired
    H2Repository (JPAUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findById(String email) {
        Optional<UserEntity> user = jpaUserRepository.findById(email);
        if(user.isPresent()){
            return Optional.of(user.get().toUser());
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(new UserEntity(user.getEmail()));
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<List<User>> getAll() {
        return Optional.empty();
    }
}
