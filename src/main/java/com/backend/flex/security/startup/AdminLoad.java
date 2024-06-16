package com.backend.flex.security.startup;

import com.backend.flex.model.security.ERole;
import com.backend.flex.model.security.Role;
import com.backend.flex.model.security.User;
import com.backend.flex.repository.RoleRepository;
import com.backend.flex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.HashSet;
import java.util.Set;

public class AdminLoad implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading admin...");

        if(userRepository.existsByUsername("admin")){
            System.out.println("Admin already loaded");
            return;
        }

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        User user = new User("admin", "admin@example.com", encoder.encode("password123"));
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);

        System.out.println("Admin loaded");
    }
}
