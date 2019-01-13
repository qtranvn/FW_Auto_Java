package com.qtr.core.config.driver.selenium.browser;

import com.qtr.core.config.driver.selenium.ConfigDriver;
import com.qtr.core.config.driver.selenium.ExecutableDriver;
import com.qtr.core.config.driver.selenium.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverConfig extends ConfigDriver implements SeleniumDriver {

    private ChromeOptions options;

    private ChromeOptions getOptions() {
        if (options == null)
            options = new ChromeOptions();
        return options;
    }

    @Override
    public WebDriver createDriver() {
        new ExecutableDriver("chrome");
        WebDriver driver = new ChromeDriver(getOptions());
        manage(driver);
        return driver;
    }

    @Override
    public void setDriverOptions(Object options) {
        this.options = (ChromeOptions) options;
    }

}
