package com.backend.flex.model.components.props.schedule;

import com.backend.flex.converter.ScheduleConverter;
import com.backend.flex.model.components.props.Prop;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SHP")
@Table(name = "schedule_props")
public class ScheduleProps implements Prop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = ScheduleConverter.class)
    @Column(name = "data", columnDefinition = "TEXT")
    private Activities data;

    public ScheduleProps() {
    }

    public ScheduleProps(Activities data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Activities getData() {
        return data;
    }

    public void setData(Activities data) {
        this.data = data;
    }
}
