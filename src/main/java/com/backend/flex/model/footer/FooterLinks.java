package com.backend.flex.model.footer;

import com.backend.flex.model.Page;
import com.backend.flex.model.footer.Footer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * This class is mapped to the "footer_links" table in the database.
 * Used by the FooterController
 */
@Entity
@Table(name = "footer_links")
public class FooterLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name="name")
    private String name;

    @Column (name="url")
    private String url;

    @JsonBackReference(value = "footer-links")
    @ManyToOne
    @JoinColumn(name = "footer_id")
    private Footer footer;

    @JsonBackReference(value = "page-footerLink")
    @OneToOne
    @JoinColumn(name = "page_id")
    private Page page;


    public FooterLinks(){}

    public FooterLinks(String name, String url, Footer footer, Page page) {
        this.name = name;
        this.url = url;
        this.footer = footer;
        this.page = page;
    }

    public long getId() {
        return id;
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

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "FooterLinks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
