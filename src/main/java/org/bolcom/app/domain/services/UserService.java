package org.bolcom.app.domain.services;

import org.bolcom.app.domain.models.User;

import java.util.List;

public interface UserService {
    public User getUserById(String id);

    public void addUser(String email);

    public void deleteUserById(String id);

    public User updateUserById(String id);

    public List<User> getAllUsers();
}
