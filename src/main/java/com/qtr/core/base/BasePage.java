package com.qtr.core.base;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

    public void openURL(String url) {
        WebDriverFactory.instance().getWebDriver().get(url);
    }

    protected static void clickJS(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor)WebDriverFactory.instance().getWebDriver();
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected static Boolean waitForElementVisible(WebElement webElement, int waitTime){
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.instance().getWebDriver(), waitTime);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (Exception t) {
            return false;
        }

    }

    protected static void setText(WebElement webElement, String value) {
        int defaulrWaitTime = 5;
        if(waitForElementVisible(webElement, defaulrWaitTime))
            webElement.clear();
            webElement.sendKeys(value);
    }

    protected static void verifyElementPresent(WebElement webElement) {
        Assert.assertEquals(webElement.isDisplayed(), true, "Web element " + webElement  + " is not displayed ");
    }

    public static void verifyTextPresent(String textValue, Boolean isContain) {
        String xpath;
        if(isContain)
            xpath = "//*[contains(text(),\"" + textValue + "\")]";
        else xpath = "//*[normalize-space(text())=\"" + textValue + "\"]";
        WebElement e = WebDriverFactory.instance().getWebDriver().findElement(By.xpath(xpath));
        verifyElementPresent(e);
    }

}
