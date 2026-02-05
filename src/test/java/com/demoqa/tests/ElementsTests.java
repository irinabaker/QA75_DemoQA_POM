package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttons;
    TextBoxPage textBox;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonsPage(driver);
        textBox = new TextBoxPage(driver);
        new HomePage(driver).getElements();
    }

    @Test
    public void doubleClickTest() {
        sidePanel.getButtons();
        buttons.doubleClick()
                .verifyDoubleClick("double click");
    }

    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttons.rightClick()
                .verifyRightClick("right click");
    }

    @Test
    public void autoCompleteTest() {
        sidePanel.getTextBox();
        textBox.autocomplete("Friedrichstr. 12, Berlin")
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxParametersTest(String name, String email, String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void textBoxWithCsvFileTest(String name, String email, String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }
}
