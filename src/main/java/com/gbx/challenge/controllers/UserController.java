package com.gbx.challenge.controllers;

import com.gbx.challenge.domain.user.RequestUser;
import com.gbx.challenge.domain.user.User;
import com.gbx.challenge.domain.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid RequestUser data){
        return ResponseEntity.ok(repository.save(User.builder()
                        .name(data.name())
                        .cpf(data.cpf())
                        .date_of_birth(data.date_of_birth())
                .build()));
    }
}
