package com.backend.flex.repository;

import com.backend.flex.model.components.props.showcase.ShowProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowcasePropRepository extends JpaRepository<ShowProps, Long>{
}
