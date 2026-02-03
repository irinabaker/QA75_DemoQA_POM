package com.demoqa.core;

import com.demoqa.utils.MyTestWatcher;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@ExtendWith(MyTestWatcher.class)
public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();
      //  ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
       // options.addArguments("--headless=new");// without opening a browser windows
       // options.addArguments("--windows-size=800,600");
        driver.get("https://demoqa.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

//    @AfterEach
//    public void tearDown() {
//        if (driver!=null) {
//            driver.quit();
//        }
//    }
}
