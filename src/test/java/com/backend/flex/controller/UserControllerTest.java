package com.backend.flex.controller;

import com.backend.flex.model.security.User;
import com.backend.flex.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
    }

    @Test
    public void getUsersReturnsAllUsers() {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
        ResponseEntity<List<User>> response = userController.gerUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    public void deleteUserReturnsNoContentWhenSuccessful() {
        doNothing().when(userService).deleteUserById(1L);
        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteUserReturnsInternalServerErrorWhenExceptionThrown() {
        doThrow(new RuntimeException()).when(userService).deleteUserById(1L);
        ResponseEntity<?> response = userController.deleteUser(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
