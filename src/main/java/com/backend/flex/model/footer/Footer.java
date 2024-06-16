package com.backend.flex.model.footer;

import com.backend.flex.model.Website;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * This class is mapped to the "footer" table in the database.
 */
@Entity
@Table(name="footer")
public class Footer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "bottom_text")
    private String bottomText;

    @Column (name = "background_color")
    private String backgroundColor;

    @Column (name = "linkColor")
    private String linkColor;

    @JsonBackReference(value = "website-footer")
    @OneToOne
    @JoinColumn(name = "website_id")
    private Website website;

    @JsonManagedReference(value = "footer-links")
    @OneToMany(mappedBy = "footer", cascade = CascadeType.REMOVE)
    private List<FooterLinks> links;

    public Footer() {
    }

    public Footer(String bottomText, String backgroundColor, String linkColor,  Website website)  {
        this.website = website;
        this.backgroundColor = backgroundColor;
        this.linkColor = linkColor;
        this.bottomText = bottomText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
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

    public List<FooterLinks> getLinks() {
        return links;
    }

    public void setLinks(List<FooterLinks> links) {
        this.links = links;
    }
}
