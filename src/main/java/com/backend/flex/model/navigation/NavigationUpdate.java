package com.backend.flex.model.navigation;

/**
 * Class used by the Navigation Controller to update Navigation
 */
public class NavigationUpdate {
    private String logo;
    private String backgroundColor;
    private String linkColor;

    public NavigationUpdate(
            String logo,
            String backgroundColor,
            String linkColor
    ) {
        this.logo = logo;
        this.backgroundColor = backgroundColor;
        this.linkColor = linkColor;
    }

    public String getLogo() {
        return logo;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getLinkColor() {
        return linkColor;
    }

}
