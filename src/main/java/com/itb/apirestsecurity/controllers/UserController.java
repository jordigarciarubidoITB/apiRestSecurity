package com.itb.apirestsecurity.controllers;

import com.itb.apirestsecurity.model.entities.User;
import com.itb.apirestsecurity.model.entities.UserConsultDTO;
import com.itb.apirestsecurity.model.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /*
    {
    "username":"Jordi",
    "password":"1234",
    "avatar":""
    }
     */

    @GetMapping("/me")
    public UserConsultDTO whoIAm(@AuthenticationPrincipal User user) {
        return new UserConsultDTO(user.getUsername(), user.getAvatar(), user.getRol());
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        try {
            User res = userService.newUser(newUser);
            UserConsultDTO user = new UserConsultDTO(res.getUsername(), res.getAvatar(), res.getRol());
            return new ResponseEntity<UserConsultDTO>(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsersInfo() {
        List<User> res = userService.listUsers();
        List<UserConsultDTO> aux = new ArrayList();
        for (User user : res) {
            aux.add(new UserConsultDTO(user.getUsername(), user.getAvatar(), user.getRol()));
        }
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(aux);
    }
}