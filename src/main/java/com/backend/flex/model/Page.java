package com.backend.flex.model;

import com.backend.flex.model.components.Components;
import com.backend.flex.model.footer.FooterLinks;
import com.backend.flex.model.navigation.Navigation;
import com.backend.flex.model.navigation.NavigationLinks;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * This class is mapped to the "pages" table in the database.
 */

@Entity
@Table(name="pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name="title")
    private String title;

    @Column (name="slug")
    private String slug;

    @Column (name="sub_route")
    private Boolean sub;

    @Column (name="hidden")
    private Boolean hidden;

    @JsonManagedReference(value = "parentPage-childPage")
    @OneToMany(mappedBy = "parentPage", cascade = CascadeType.ALL)
    private List<Page> page;

    @JsonBackReference(value = "website-page")
    @ManyToOne
    @JoinColumn(name = "website_id", nullable = false)
    private Website website;

    @JsonManagedReference(value = "page-component")
    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<Components> components;

    @JsonBackReference(value = "parentPage-childPage")
    @ManyToOne
    @JoinColumn(name = "parent_page_id")
    private Page parentPage;

    @JsonManagedReference(value = "page-navigationLink")
    @OneToOne(mappedBy = "page", cascade = CascadeType.REMOVE)
    private NavigationLinks navigationLink;

    @JsonManagedReference(value = "page-footerLink")
    @OneToOne(mappedBy = "page", cascade = CascadeType.REMOVE)
    private FooterLinks footerLinks;

    public Page(){
    }

    public Page(String slug, String title, Boolean sub, Website website){
        this.slug=slug;
        this.website = website;
        this.sub = sub;
        this.title = title;
        this.hidden = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public Website getWebsite() {
        return website;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getSub() {
        return sub;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub(Boolean sub) {
        this.sub = sub;
    }

    public Page getParentPage() {
        return parentPage;
    }

    public List<Page> getPage() {
        return page;
    }

    public void setPage(List<Page> page) {
        this.page = page;
    }

    public void setParentPage(Page parentPage) {
        this.parentPage = parentPage;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public NavigationLinks getNavigationLink() {
        return navigationLink;
    }

    public void setNavigationLink(NavigationLinks navigationLink) {
        this.navigationLink = navigationLink;
    }
}
