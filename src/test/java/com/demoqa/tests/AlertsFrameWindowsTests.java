package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWidows.AlertsPage;
import com.demoqa.pages.alertsFrameWidows.IframesPage;
import com.demoqa.pages.alertsFrameWidows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    IframesPage iframes;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).getAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        iframes = new IframesPage(driver);
    }

    @Test
    @DisplayName("Verify to -> Alert appears after 5 second")
    public void waitAlertTest() {
        sidePanel.getAlerts();
        alerts.verifyAlertWithTimer();
    }

    @Test
    @DisplayName("Select 'Cancel'")
    public void selectResulTest() {
        sidePanel.getAlerts();
        alerts.clickOnConfirmButton()
                .selectResult("Cancel")
                .verifyResult("Cancel");
    }

    @Test
    @DisplayName("Displayed message 'Hello world!!!")
    public void sendMessageToAlertTest() {
        sidePanel.getAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello world!!!")
                .verifyMessage("Hello world!!!");
    }

    @Test
    @DisplayName("New tab opened")
    @Tag("smoky")
    public void newTabTest() {
        sidePanel.getBrowserWindows();
        new WindowsPage(driver).clickOnNewTabButton()
                .switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
    }

    @Test
    public void switchToIframesByIndexTest() {
        sidePanel.getFrames();
        iframes.returnListOfIframes()
                .switchToIframeByIndex(1)
                .verifyIframeByTitle("This is a sample page")
                ;
    }

    @Test
    public void switchToIframeByIdTest() {
        sidePanel.getFrames();
        iframes.switchToIframeById()
                .verifyIframeByTitle("This is a sample page")
                .switchToMainPage()
                .verifyMainPageTitle("Frames")
        ;
    }

    @Test
    public void nestedIframesTest() {
        sidePanel.getNestedFrames();
        iframes.verifyNestedIframes();
    }
}
