package com.backend.flex.model;

import com.backend.flex.model.components.Components;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="registered")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "email")
    private String email;

    @Column (name = "newsletter")
    private Boolean newsletter;

    @JsonBackReference(value = "website-register")
    @ManyToOne
    @JoinColumn(name = "website_id", nullable = false)
    private Website website;

    public Register() {}

    public Register(String name, String surname, String email, Boolean newsletter) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.newsletter = newsletter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        //LOGIC FOR LATER
//        boolean res  = website.getComponents().stream().anyMatch((obj -> obj.getType().equals("RPR")));
//        if(res){
//            this.website = website;
//        }
        this.website = website;
    }

}
