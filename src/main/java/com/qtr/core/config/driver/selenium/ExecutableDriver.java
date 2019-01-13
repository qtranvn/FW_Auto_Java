package com.qtr.core.config.driver.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;

public class ExecutableDriver {

    private final String browserName;
    private String driverVersion;

    public ExecutableDriver(String browserName, String driverVersion) {
        this.browserName = browserName;
        this.driverVersion = driverVersion;
        setDriverExecutable();
    }

    public ExecutableDriver(String browserName) {
        this.browserName = browserName;
        setDriverExecutable();
    }

    private void setDriverExecutable() {
        //Set directory that contains webdrivers to .\drivers
        WebDriverManager.config().setTargetPath("drivers");

        //Initiate new webdriver instance base on browserName
        switch(browserName.toLowerCase().trim()) {
            case "chrome":
                if (driverVersion == null)
                    WebDriverManager.chromedriver().setup();
                else
                    WebDriverManager.chromedriver().version(driverVersion).setup();
                String pathToDriver = WebDriverManager.chromedriver().getBinaryPath();
                System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, pathToDriver);
                break;
        }
    }

}
