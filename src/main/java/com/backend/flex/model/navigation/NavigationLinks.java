package com.backend.flex.model.navigation;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.Components;
import com.backend.flex.model.navigation.Navigation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * This class is mapped to the "navigation_links" table in the database.
 * Used by the NavigationController
 */

@Entity
@Table(name = "navigation_links")
public class NavigationLinks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name="name")
    private String name;

    @Column (name="url")
    private String url;

    @JsonBackReference(value = "navigation-links")
    @ManyToOne
    @JoinColumn(name = "navigation_id")
    private Navigation navigation;

    @JsonBackReference(value = "page-navigationLink")
    @OneToOne
    @JoinColumn(name = "page_id")
    private Page page;


    public NavigationLinks(){}

    public NavigationLinks(String name, String url, Navigation navigation, Page page) {
        this.name = name;
        this.url = url;
        this.navigation = navigation;
        this.page = page;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
