package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.bookStore.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".card:nth-child(6)")
    WebElement bookStore;

    public LoginPage getBookStore() {
        click(bookStore);
        return new LoginPage(driver);
    }

    @FindBy(css = ".card:nth-child(3)")
    WebElement alertsFrameWindows;

    public SidePanel getAlertsFrameWindows() {
        click(alertsFrameWindows);
        return new SidePanel(driver);
    }
}
