package org.bolcom.app.application.controllers;

import org.bolcom.app.domain.services.UserService;
import org.bolcom.app.domain.valueobjects.Email;
import org.bolcom.app.application.requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games/mancala/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequest addUserRequest){

        userService.addUser(new Email(addUserRequest.getEmail()));
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }

}
