package com.qtr.core.config.driver.selenium;

import org.openqa.selenium.WebDriver;

public interface SeleniumDriver {

    WebDriver createDriver();

    void setDriverOptions(Object options);
}
