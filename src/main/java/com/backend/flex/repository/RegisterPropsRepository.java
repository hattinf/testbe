package com.backend.flex.repository;

import com.backend.flex.model.components.props.RegisterProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterPropsRepository extends JpaRepository<RegisterProps, Long> {
}
