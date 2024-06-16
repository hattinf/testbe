package com.backend.flex.model.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
    }

    @Test
    public void roleSetsAndGetsId() {
        long expectedId = 1L;
        role.setId(expectedId);
        assertEquals(expectedId, role.getId());
    }

    @Test
    public void roleSetsAndGetsName() {
        ERole expectedName = ERole.ROLE_USER;
        role.setName(expectedName);
        assertEquals(expectedName, role.getName());
    }

}
