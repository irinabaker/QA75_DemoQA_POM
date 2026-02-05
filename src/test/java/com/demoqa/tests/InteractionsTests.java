package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.interactions.DragAndDropPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InteractionsTests extends TestBase {

    SidePanel sidePanel;
    DragAndDropPage dragAndDrop;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        dragAndDrop = new DragAndDropPage(driver);
        new HomePage(driver).getInteractions();
    }

    @Test
    public void dragMeTest() {
        sidePanel.getDroppable();
        dragAndDrop.dragMe()
                .verifyDropped("Dropped!");
    }

    @Test
    public void dragMeByTest() {
        sidePanel.getDroppable();
        dragAndDrop.dragMeBy()
                .verifyDropped("Dropped!")
                ;
    }
}
