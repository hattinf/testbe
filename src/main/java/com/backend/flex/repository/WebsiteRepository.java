package com.backend.flex.repository;

import com.backend.flex.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
    List<Website> findByUserId(Long id);

}
