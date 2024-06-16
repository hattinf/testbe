package com.backend.flex.security.startup;


import com.backend.flex.model.security.ERole;
import com.backend.flex.model.security.Role;
import com.backend.flex.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class RoleLoad implements CommandLineRunner {

        @Autowired
        private RoleRepository roleRepository;

        @Override
        public void run(String... args) throws Exception {
            System.out.println("Loading roles...");
            if(roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()){
                    System.out.println("Roles already loaded");
                    return;
            }

            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            System.out.println("Roles loaded");
        }
}
