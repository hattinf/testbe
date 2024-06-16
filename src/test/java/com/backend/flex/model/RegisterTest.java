package com.backend.flex.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegisterTest {

    private Register register;

    @BeforeEach
    public void setup(){
        register = new Register();
    }

    @Test
    public void registerSetsAndGetsId() {
        long expected = 1L;
        register.setId(expected);
        assertEquals(expected, register.getId());
    }

    @Test
    public void registerSetsAndGetsName() {
        String expected = "test";
        register.setName(expected);
        assertEquals(expected, register.getName());
    }

    @Test
    public void registerSetsAndGetsEmail() {
        String expected = "test";
        register.setEmail(expected);
        assertEquals(expected, register.getEmail());
    }

    @Test
    public void registerSetsAndGetsSurname() {
        String expected = "test";
        register.setSurname(expected);
        assertEquals(expected, register.getSurname());
    }

    @Test
    public void registerSetsAndGetsNewsletter() {
        boolean expected = true;
        register.setNewsletter(expected);
        assertEquals(expected, register.getNewsletter());
    }

    @Test
    public void registerSetsAndGetsWebsite() {
        Website expected = new Website();
        register.setWebsite(expected);
        assertEquals(expected, register.getWebsite());
    }

    @Test
    public void registerInit(){
        Register register = new Register(
                "test",
                "test",
                "test",
                true
        );
        assertEquals("test", register.getName());
        assertEquals("test", register.getSurname());
        assertEquals("test", register.getEmail());
        assertEquals(true, register.getNewsletter());
    }
}
