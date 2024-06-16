package com.backend.flex.repository;

import com.backend.flex.model.components.Components;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Components, Long> {
}

