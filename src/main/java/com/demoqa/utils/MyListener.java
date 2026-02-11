package com.demoqa.utils;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class MyListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    private final WebDriver driver;

    public MyListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
       logger.error("We have a problem!!!");
       logger.error("********************************");
       logger.error("Method --> {}",method.getName());
        logger.error("********************************");
        logger.error("Target exception --> {}",e.getCause());
        logger.error("********************************");

        int i = new Random().nextInt(1000);
        String link = "screenshots/screen-" + i + ".png";
        logger.error("Screen with error -> {}",link);

        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException ex) {
            logger.error("Failed to save screenshot",ex);
        }
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("We opened web site {}",url);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        logger.info("Total elements is {}",result.size());
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("We clicked on {}",element);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("We send keys {} to element {}", element,keysToSend);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
       logger.info("Element contains {}",element.getText());
    }
}
