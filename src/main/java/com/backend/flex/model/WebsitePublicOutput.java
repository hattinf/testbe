package com.backend.flex.model;

import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.navigation.Navigation;

/**
 * This class is mapped to the "websites" table in the database.
 */
public class WebsitePublicOutput {

    private long id;

    private String request;

    private String name;

    private Page page;

    private Footer footer;

    private Navigation navigation;

    public WebsitePublicOutput(){}

    public WebsitePublicOutput(long id, String name, Page page, Footer footer, Navigation navigation, String request) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.footer = footer;
        this.navigation = navigation;
        this.request = request;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Page getPage() {
        return page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPage(Page page) {
        this.page = page;
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

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", page=" + page +
                '}';
    }
}
