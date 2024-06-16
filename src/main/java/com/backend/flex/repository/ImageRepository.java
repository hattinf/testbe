package com.backend.flex.repository;

import com.backend.flex.model.components.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
