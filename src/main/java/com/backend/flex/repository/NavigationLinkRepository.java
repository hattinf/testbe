package com.backend.flex.repository;

import com.backend.flex.model.navigation.NavigationLinks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NavigationLinkRepository extends JpaRepository<NavigationLinks, Long> {
}
