package com.backend.flex.model.footer;
/**
 * Class used by the Footer Controller to create new Footer
 */
public class FooterCreate {
    private String bottomText;
    private String backgroundColor;
    private String linkColor;
    private Long website;

    public FooterCreate() {}

    public FooterCreate(String bottomText,String backgroundColor, String linkColor, Long website) {
        this.website = website;
        this.backgroundColor = backgroundColor;
        this.linkColor = linkColor;
        this.bottomText = bottomText;
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

    public Long getWebsite() {
        return website;
    }

    public void setWebsite(Long website) {
        this.website = website;
    }
}
