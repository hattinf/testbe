package com.backend.flex.model;
import com.backend.flex.model.footer.Footer;
import com.backend.flex.model.navigation.Navigation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebsitePublicOutputTest {

    private WebsitePublicOutput websitePublicOutput;

    @BeforeEach
    public void setUp() {
        websitePublicOutput = new WebsitePublicOutput();
        WebsitePublicOutput websitePublicOutput = new WebsitePublicOutput(1L, "Test Name", new Page(), new Footer(), new Navigation(), "Test Request");

    }

    @Test
    public void websitePublicOutputSetsAndGetsId() {
        long expectedId = 1L;
        websitePublicOutput.setId(expectedId);
        assertEquals(expectedId, websitePublicOutput.getId());
    }

    @Test
    public void websitePublicOutputSetsAndGetsName() {
        String expectedName = "Test Name";
        websitePublicOutput.setName(expectedName);
        assertEquals(expectedName, websitePublicOutput.getName());
    }

    @Test
    public void websitePublicOutputSetsAndGetsPage() {
        Page expectedPage = new Page();
        websitePublicOutput.setPage(expectedPage);
        assertEquals(expectedPage, websitePublicOutput.getPage());
    }

    @Test
    public void websitePublicOutputSetsAndGetsFooter() {
        Footer expectedFooter = new Footer();
        websitePublicOutput.setFooter(expectedFooter);
        assertEquals(expectedFooter, websitePublicOutput.getFooter());
    }

    @Test
    public void websitePublicOutputSetsAndGetsNavigation() {
        Navigation expectedNavigation = new Navigation();
        websitePublicOutput.setNavigation(expectedNavigation);
        assertEquals(expectedNavigation, websitePublicOutput.getNavigation());
    }

    @Test
    public void websitePublicOutputToStringReturnsCorrectFormat() {
        String expectedToString = "Website{id=0, name='null', page=null}";
        assertEquals(expectedToString, websitePublicOutput.toString());
    }

    @Test
    public void websitePublicOutputSetsAndGetsRequest(){
        String  expectedRequest = "Test Request";
        websitePublicOutput.setRequest(expectedRequest);
        assertEquals(expectedRequest, websitePublicOutput.getRequest());
    }
}