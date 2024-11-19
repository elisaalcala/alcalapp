package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User("john_doe", "encodedPassword123", "ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    public void testConstructor() {
        // Verifica que el constructor inicialice correctamente los valores
        assertThat(user.getName()).isEqualTo("john_doe");
        assertThat(user.getEncodedPassword()).isEqualTo("encodedPassword123");
        assertThat(user.getRoles()).containsExactly("ROLE_USER", "ROLE_ADMIN");
    }

    @Test
    public void testGettersSetters() {
        User newUser = new User();
        
        newUser.setName("alice_smith");
        newUser.setEncodedPassword("newEncodedPassword");
        newUser.setRoles(List.of("ROLE_USER"));

        // Verifica los valores establecidos con los setters
        assertThat(newUser.getName()).isEqualTo("alice_smith");
        assertThat(newUser.getEncodedPassword()).isEqualTo("newEncodedPassword");
        assertThat(newUser.getRoles()).containsExactly("ROLE_USER");
    }


}
