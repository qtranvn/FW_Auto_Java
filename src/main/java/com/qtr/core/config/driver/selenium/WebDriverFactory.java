package com.qtr.core.config.driver.selenium;

import com.qtr.core.config.driver.selenium.browser.ChromeDriverConfig;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    private volatile static WebDriverFactory webDriverFactory;
    private Map<Long, WebDriver> driverStorage = new HashMap<>();
    private WebDriver webDriver;

    private WebDriverFactory() { }

    public static WebDriverFactory instance() {
        if(webDriverFactory == null) {
            synchronized(WebDriverFactory.class) {
                if(webDriverFactory == null)
                    webDriverFactory = new WebDriverFactory();
            }
        }
        return webDriverFactory;
    }

    public void createWebDriver(String browserName) {
        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                webDriver = new ChromeDriverConfig().createDriver();
                break;
        }
        setDriverStorage(webDriver);
    }

    private void setDriverStorage(WebDriver webDriver) {
        driverStorage.put(Thread.currentThread().getId(), webDriver);
    }

    public WebDriver getWebDriver() {
        if (driverStorage.size() == 0) {
            System.out.println("*****No existing thread id\n");
            return null;
        }

        if (driverStorage.containsKey(Thread.currentThread().getId()))
            return driverStorage.get(Thread.currentThread().getId());

        return driverStorage.get(0);
    }

    public void disposeWebDriver() {
        if (driverStorage.containsKey(Thread.currentThread().getId())) {
            driverStorage.get(Thread.currentThread().getId()).quit();
        }
        driverStorage.remove(Thread.currentThread().getId());
    }

    public void disposeAllDriver() {
        for (Map.Entry<Long, WebDriver> driver : driverStorage.entrySet()) {
            WebDriver value = driver.getValue();
            value.quit();
        }
    }
}

