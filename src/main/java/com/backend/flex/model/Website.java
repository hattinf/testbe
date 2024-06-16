package com.backend.flex.model;

import com.backend.flex.model.components.Components;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

/**
 * This class is mapped to the "websites" table in the database.
 */
@Entity
@Table(name = "websites")
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "name")
    private String name;

    @Column (name = "published")
    private Boolean published;

    @Column (name = "description")
    private String description;

    @JsonManagedReference(value = "website-page")
    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL)
    private List<Page> page;

    @JsonManagedReference(value = "website-footer")
    @OneToOne(mappedBy = "website", cascade = CascadeType.REMOVE)
    private Footer footer;

    @JsonManagedReference(value = "website-navigation")
    @OneToOne(mappedBy = "website", cascade = CascadeType.REMOVE)
    private Navigation navigation;

    @JsonManagedReference(value = "website-components")
    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL)
    private List<Components> components;

    @JsonIgnore
    @JsonManagedReference(value = "website-register")
    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL)
    private List<Register> register;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private User user;


    public Website(){}

    public Website(String name, User user) {
        this.name = name;
        this.published = false;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public long setId(long id) {
        return this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Page> getPage() {
//        if (page == null) {
//            return Collections.emptyList();
//        }
//        return page.stream().filter(obj -> obj.getSub().equals(false)).toList();
        return page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public void setPage(List<Page> page) {
        this.page = page;
    }

    public void setComponents(List<Components> components) {
        this.components = components;
    }

    public List<Components> getComponents() {
        return components;
    }

    public List<Register> getRegister() {
        return register;
    }

    public void setRegister(List<Register> register) {
        this.register = register;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", published=" + published +
                ", description='" + description + '\'' +
                ", page=" + page +
                ", footer=" + footer +
                ", navigation=" + navigation +
                ", components=" + components +
                ", register=" + register +
                ", user=" + user +
                '}';
    }
}
