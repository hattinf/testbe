package com.backend.flex.repository;

import com.backend.flex.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {
   List<Register> findByWebsiteId(Long websiteId);
   Optional<Register> findByEmail(String email);
}
