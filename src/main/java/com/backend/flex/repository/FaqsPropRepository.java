package com.backend.flex.repository;

import com.backend.flex.model.components.props.faq.FaqProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqsPropRepository extends JpaRepository<FaqProps, Long> {
}
