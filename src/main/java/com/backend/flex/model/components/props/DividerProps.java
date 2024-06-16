package com.backend.flex.model.components.props;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("HRP")
@Table(name = "divider_props")
public class DividerProps implements  Prop{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private Integer size;

    public DividerProps() {
    }

    public DividerProps(String color, Integer size) {
        this.color = color;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
