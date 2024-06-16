package com.backend.flex.repository;

import com.backend.flex.model.components.props.HeroProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroPropsRepository extends JpaRepository<HeroProps, Long> {
}
