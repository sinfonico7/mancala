package org.bolcom.app.domain.services;

import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.valueobjects.Email;

import java.util.List;

public interface UserService {
    public User getUserById(String id);

    public void addUser(Email email);

    public void deleteUserById(String id);

    public User updateUserById(Email id);

    public List<User> getAllUsers();
}
