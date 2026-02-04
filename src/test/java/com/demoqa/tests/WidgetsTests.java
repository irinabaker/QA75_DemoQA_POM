package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WidgetsTests extends TestBase {

    SidePanel sidePanel;
    SelectPage select;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        select = new SelectPage(driver);
        new HomePage(driver).getWidgets();
    }

    @Test
    public void oldStyleSelectMenuTest() {
        sidePanel.getSelectMenu();
        select.oldStyleSelect("Indigo")
                .verifyColor();
    }

    @Test
    public void multiSelectTest() {
        sidePanel.getSelectMenu();
        select.multiSelectDropDown(new String[]{"Green","Red"})
                .verifyMultiSelect(new String[]{"Green","Red"});
    }

    @Test
    public void standardMultiSelectTest() {
        sidePanel.getSelectMenu();
        select.verifySelectedCar("opel","#dfc5e0")
               // ("opel","rgba(223, 197, 224, 1)")
        ;
    }

    @Test
    public void hoverMouseOnMenuTest() {
        sidePanel.getMenu();
        new MenuPage(driver).hoverMouseSubMenu()
                .verifySubMenu();
    }

    @Test
    public void sliderTest() {
        sidePanel.getSlider();
        new SliderPage(driver).moveSliderToHorizontalDirection()
                .verifySliderValue("100")
        ;
    }
}
