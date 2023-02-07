package org.bolcom.app.domain.services;

import org.bolcom.app.domain.exceptions.UserException;
import org.bolcom.app.domain.models.User;
import org.bolcom.app.domain.repositories.UserRepository;
import org.bolcom.app.domain.valueobjects.Email;

import java.util.List;
import java.util.Optional;


public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    public DomainUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void addUser(Email email) {
        userRepository.save(new User(email));
    }

    @Override
    public void deleteUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new UserException("The User doesn't exists",404);
        userRepository.delete(user.get());
    }

    @Override
    public User updateUserById(Email id) {
        Optional<User> user = userRepository.findById(id.toString());
        if(!user.isPresent()){
            userRepository.save(new User(id));
        }
        return userRepository.update(user.get());
    }

    @Override
    public List<User> getAllUsers() {
        Optional<List<User>> users = userRepository.getAll();
        if(users.isEmpty()) throw new UserException("There is no users",404);
        return users.get();
    }
}
