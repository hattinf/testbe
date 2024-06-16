package com.backend.flex.repository;

import com.backend.flex.model.navigation.Navigation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavigationRepository extends JpaRepository<Navigation, Long> {
}
