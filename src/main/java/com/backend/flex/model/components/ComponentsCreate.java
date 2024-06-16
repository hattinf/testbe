package com.backend.flex.model.components;

import com.backend.flex.model.components.props.HeroProps;

/**
 * Class used by the Component Controller to create new Components
 */
public class ComponentsCreate {
    private Long page;
    private Object props;
    private String name;
    private String type;
    private Integer position;

    public ComponentsCreate(){
    }

    public ComponentsCreate(Long page, Object props, String name, String type, Integer position) {
        this.page = page;
        this.props = props;
        this.name = name;
        this.type = type;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public Long getPage() {
        return page;
    }

    public Object getProps() {
        return props;
    }

    public void setType(String name) {
        this.type = name;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public void setProps(Object props) {
        this.props = props;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
