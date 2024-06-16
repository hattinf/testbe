package com.backend.flex.repository;

import com.backend.flex.model.components.props.schedule.ScheduleProps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulePropRepository extends JpaRepository<ScheduleProps, Long> {

}
