package com.demoqa.pages.interactions;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage extends BasePage {

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "draggable")
    WebElement dragMe;
    @FindBy(id = "droppable")
    WebElement dropHere;

    public DragAndDropPage dragMe() {
        scrollWithJS(0,300,500);
        actions.dragAndDrop(dragMe,dropHere).perform();
        return this;
    }

    public DragAndDropPage verifyDropped(String text) {
        Assertions.assertTrue(shouldHaveText(dropHere,text,3));
        return this;
    }

    public DragAndDropPage dragMeBy() {
        scrollWithJS(0,100,500);
        //get coordinates dragMe(from)
        int xOffset1 = dragMe.getLocation().getX();
        int yOffset1 = dragMe.getLocation().getY();
        System.out.println("xOffset1 -> " + xOffset1 + " *** " + "yOffset1 -> " + yOffset1);

        //get coordinates dropHere(to)
        int xOffset = dropHere.getLocation().getX();
        int yOffset = dropHere.getLocation().getY();
        System.out.println("xOffset -> " + xOffset + " *** " + "yOffset -> " + yOffset);

        //get difference offset and offset1
        xOffset = (xOffset - xOffset1)+60;
        yOffset = (yOffset - yOffset1)+100;

        actions.dragAndDropBy(dragMe,xOffset,yOffset).perform();
        return this;
    }
}
