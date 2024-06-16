package com.backend.flex.model.footer;

/**
 * Class used by the Footer Controller to update Footer
 */
public class FooterUpdate {
    private String bottomText;
    private String backgroundColor;
    private String linkColor;

    public FooterUpdate() {
    }

    public String getBottomText() {
        return bottomText;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getLinkColor() {
        return linkColor;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setLinkColor(String linkColor) {
        this.linkColor = linkColor;
    }
}
