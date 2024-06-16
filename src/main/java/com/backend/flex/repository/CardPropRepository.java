package com.backend.flex.repository;

import com.backend.flex.model.components.props.cards.CardProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardPropRepository extends JpaRepository<CardProps, Long> {
}
