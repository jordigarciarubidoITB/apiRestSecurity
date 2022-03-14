package com.itb.apirestsecurity.model.services;

import com.itb.apirestsecurity.model.entities.User;
import com.itb.apirestsecurity.model.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder xifrat;

    public User consultByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User newUser(User newUser) {
        newUser.setPassword(xifrat.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return newUser;
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
