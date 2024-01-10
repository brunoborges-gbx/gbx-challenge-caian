package com.gbx.challenge.controllers;

import com.gbx.challenge.domain.user.RequestUser;
import com.gbx.challenge.domain.user.User;
import com.gbx.challenge.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUsers = repository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid RequestUser data){
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
