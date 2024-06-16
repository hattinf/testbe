package com.backend.flex.service;

import com.backend.flex.model.security.User;
import com.backend.flex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Retrieves a user by its ID.
     *
     * @return an Optional containing the requested user, or empty if not found
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve
     */
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
