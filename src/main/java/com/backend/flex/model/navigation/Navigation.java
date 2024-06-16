package com.backend.flex.model.navigation;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * This class is mapped to the "navigation" table in the database.
 */

@Entity
@Table(name = "navigation")
public class Navigation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "logo")
    private String logo;

    @Column (name = "background_color")
    private String backgroundColor;

    @Column (name = "linkColor")
    private String linkColor;

    @JsonBackReference(value = "website-navigation")
    @OneToOne
    @JoinColumn(name = "website_id")
    private Website website;

    @JsonManagedReference(value = "navigation-links")
    @OneToMany(mappedBy = "navigation", cascade = CascadeType.REMOVE)
    private List<NavigationLinks> links;

    public Navigation() {
    }

    public Navigation(String logo, String backgroundColor, String linkColor,  Website website)  {
        this.website = website;
        this.backgroundColor = backgroundColor;
        this.linkColor = linkColor;
        this.logo = logo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getLinkColor() {
        return linkColor;
    }

    public void setLinkColor(String linkColor) {
        this.linkColor = linkColor;
    }

    public List<NavigationLinks> getLinks() {
        return links.stream().filter( l -> !l.getPage().getHidden()).toList();
    }

    public void setLinks(List<NavigationLinks> links) {
        this.links = links;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
