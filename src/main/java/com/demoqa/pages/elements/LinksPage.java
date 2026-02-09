package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class LinksPage extends BasePage {

    public LinksPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;

    public LinksPage getAllLinks() {
        // size
        System.out.println("Total links size on the page = " + allLinks.size());
        // list
        String url = "";
        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
            System.out.println("*****************************");
        }
        return this;
    }
}
