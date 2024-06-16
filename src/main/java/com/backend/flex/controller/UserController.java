package com.backend.flex.controller;

import com.backend.flex.model.security.User;
import com.backend.flex.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * Get ALl users by Admin
     *
     * @return a ResponseEntity containing a list of Users
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<User>> gerUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    /**
     *
     * Delete user by ID by Admin
     *
     * @param id
     * @return a ResponseEntity with NO_CONTENT if successful, or INTERNAL_SERVER_ERROR if an exception occurs
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
