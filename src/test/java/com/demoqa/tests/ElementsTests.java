package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.*;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttons;
    TextBoxPage textBox;
    BrokenLinksImagesPage brokenLinksImages;
    UploadPage upload;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonsPage(driver);
        textBox = new TextBoxPage(driver);
        brokenLinksImages = new BrokenLinksImagesPage(driver);
        upload = new UploadPage(driver);
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

    @Test
    public void javascriptExecutorTest() {
        sidePanel.getTextBox();
        textBox.enterPersonalDataWithJS("Jama Musiala", "jamal@gm.com")
                .clickWithJavascript()
                .getInnerText()
                .verifyURL()
                .navigateToNewPage("https://ilcarro.web.app")
                .verifyNewPageTitle()
                ;
    }

    @Test
    @Tag("smoky")
    public void getAllLinksTest() {
        sidePanel.getLinks();
        new LinksPage(driver).getAllLinks();
    }

    @Test
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinksImages();
        brokenLinksImages.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImages() {
        sidePanel.getBrokenLinksImages();
        brokenLinksImages.checkBrokenImages();
    }

    @Test
    public void performKeyEventsWithRobotTest() {
        sidePanel.getUpload();
        upload.performKeyEvent()
                .verifyFilePath("C:\\fakepath\\D1.txt")
          ;
    }

    @Test
    public void performMouseEventWithRobotTest() {
        sidePanel.getUpload();
        upload.performMouseEvent()
                .verifyFilePath("C:\\fakepath\\D1.txt")
                ;
    }
}

