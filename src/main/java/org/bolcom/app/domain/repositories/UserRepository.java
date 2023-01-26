package org.bolcom.app.domain.repositories;

import org.bolcom.app.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String email);

    void save(User user);

    void delete(User user);

    User update(User user);

    Optional<List<User>> getAll();
}
