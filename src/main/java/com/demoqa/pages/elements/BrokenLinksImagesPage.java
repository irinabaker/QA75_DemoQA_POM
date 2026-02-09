package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrokenLinksImagesPage extends BasePage {

    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;

    public BrokenLinksImagesPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement element = allLinks.get(i);
            String url = element.getDomAttribute("href");
            verifyLinks(url);
        }
        return this;
    }

    @FindBy(css = "img")
    List<WebElement> images;

    public BrokenLinksImagesPage checkBrokenImages() {
        System.out.println("Total images in the page " + images.size());
        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String imageUrl = image.getAttribute("src");
            verifyLinks(imageUrl);

            try {
                boolean imageDisplayed = (Boolean)js.executeScript
                        ("return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0);",image);
                if (imageDisplayed) {
//                    System.out.println("DISPLAY - OK");
//                    System.out.println("***************************");
                    softly.assertThat(imageDisplayed);
                } else {
                   // System.out.println("BROKEN image is " + imageUrl);
                    softly.fail("BROKEN image is " + imageUrl);
                }
            } catch (Exception e) {
                System.out.println("ERROR occurred");
            }
        }
        softly.assertAll();
        return this;
    }
}
