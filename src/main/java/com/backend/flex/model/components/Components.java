package com.backend.flex.model.components;

import com.backend.flex.model.Page;
import com.backend.flex.model.Website;
import com.backend.flex.model.components.props.*;
import com.backend.flex.model.components.props.bar.BarProps;
import com.backend.flex.model.components.props.cards.CardProps;
import com.backend.flex.model.components.props.faq.FaqProps;
import com.backend.flex.model.components.props.schedule.Activities;
import com.backend.flex.model.components.props.schedule.ScheduleProps;
import com.backend.flex.model.components.props.showcase.ShowProps;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

/**
 * This class is mapped to the "components" table in the database.
 */
@Entity
@Table(name = "component")
public class Components {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "name")
    private String name;

    @JsonBackReference(value = "page-component")
    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    /**
     * Implemented Polymorphic Relationship between different Props entity Models and Component entity model.
     * Link bellow used to configure and code logic to handle CRUD of the set relationship
     * LINKS:
     * <a href="https://jpaobjects.sourceforge.net/m2-site/main/documentation/docbkx/html/user-guide/ch04s09.html">...</a>
     * <a href="https://docs.jboss.org/hibernate/orm/6.1/userguide/html_single/Hibernate_User_Guide.html#associations-any">...</a>
     */
    @Any(fetch = FetchType.EAGER)
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValue(discriminator = "TPR", entity = TextProps.class)
    @AnyDiscriminatorValue(discriminator = "HPR", entity = HeroProps.class)
    @AnyDiscriminatorValue(discriminator = "RPR", entity = RegisterProps.class)
    @AnyDiscriminatorValue(discriminator = "CRP", entity = CardProps.class)
    @AnyDiscriminatorValue(discriminator = "SHP", entity = ScheduleProps.class)
    @AnyDiscriminatorValue(discriminator = "DIV", entity = DividerProps.class)
    @AnyDiscriminatorValue(discriminator = "FPR", entity = FaqProps.class)
    @AnyDiscriminatorValue(discriminator = "SHC", entity = ShowProps.class)
    @AnyDiscriminatorValue(discriminator = "MDA", entity = MediaProps.class)
    @AnyDiscriminatorValue(discriminator = "BRP", entity = BarProps.class)
    @AnyDiscriminatorValue(discriminator = "IMG", entity = ImageProp.class)
    @AnyKeyJavaClass(Long.class)
    @Cascade(CascadeType.ALL)
    @Column(name = "prop_type")
    @JoinColumn(name="prop_id")
    private Prop prop;

    @Column(name="type")
    private String type;

    @Column(name="position")
    private Integer position;

    @JsonBackReference(value = "website-components")
    @ManyToOne
    @JoinColumn(name = "website_id", nullable = false)
    private Website website;

    public Components(){
    }

    public Components(String name, Page page, Prop prop, String type, Website website, Integer position) {
        this.name = name;
        this.page = page;
        this.prop = prop;
        this.type = type;
        this.position = position;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Prop getProp() {
        return prop;
    }

    public void setProp(Prop prop) {
        this.prop = prop;
    }

    public  Page getPage(){
        return page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
