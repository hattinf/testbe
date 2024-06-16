package com.backend.flex.model.navigation;

/**
 * Class used by the Navigation Controller to create new Navigation
 */
public class NavigationCreate {
        private String logo;
        private Long website;
        public NavigationCreate() {}

        public NavigationCreate(String logo, Long website) {
            this.logo = logo;
            this.website = website;
        }

        public String getLogo() {
            return logo;
        }
        public void setLogo(String logo) {
            this.logo = logo;
        }
        public Long getWebsite() {
            return website;
        }
}
