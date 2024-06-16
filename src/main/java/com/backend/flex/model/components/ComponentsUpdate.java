package com.backend.flex.model.components;

/**
 * Class used by the Component Controller to update Components
 */
public class ComponentsUpdate {
    private Object props;
    private String name;
    private String type;
    private  Integer position;

    public ComponentsUpdate() {
    }

    public String getType() {
        return type;
    }

    public Object getProps() {
        return props;
    }

    public void setType(String name) {
        this.type = name;
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
