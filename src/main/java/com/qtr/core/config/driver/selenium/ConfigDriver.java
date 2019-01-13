package com.qtr.core.config.driver.selenium;

import com.qtr.core.utils.FileHelper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ConfigDriver {

    private boolean maximize;
    private int width;
    private int height;
    private int implicitlyWait;
    private int pageLoadTimeout;
    private int scriptTimeout;

    protected ConfigDriver() {
        Properties prop = FileHelper.getProperties("driver");
        this.maximize = Boolean.valueOf(prop.getProperty("maximize"));
        this.width = Integer.valueOf(prop.getProperty("width"));
        this.height = Integer.valueOf(prop.getProperty("height"));
        this.implicitlyWait = Integer.valueOf(prop.getProperty("implicitlyWait"));
        this.pageLoadTimeout = Integer.valueOf(prop.getProperty("pageLoadTimeout"));
        this.scriptTimeout = Integer.valueOf(prop.getProperty("scriptTimeout"));
    }

    protected void manage(final WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));

        if (implicitlyWait > 0) {
            driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        }

        if (maximize) {
            driver.manage().window().maximize();
        }

        if (height > 0 && width > 0 && !maximize) {
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }

    protected boolean isMaximize() {
        return maximize;
    }

    protected void setMaximize(boolean maximize) {
        this.maximize = maximize;
    }

    protected int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        this.width = width < 0 ? 0 : width;
    }

    protected int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        this.height = height < 0 ? 0 : height;
    }

    protected int getImplicitlyWait() {
        return implicitlyWait;
    }

    protected void setImplicitlyWait(int implicitlyWait) {
        this.implicitlyWait = implicitlyWait < 0 ? 0 : implicitlyWait;
    }

    protected int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    protected void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout < 0 ? 0 : pageLoadTimeout;
    }

    protected int getScriptTimeout() {
        return scriptTimeout;
    }

    protected void setScriptTimeout(int scriptTimeout) {
        this.scriptTimeout = scriptTimeout < 0 ? 0 : scriptTimeout;
    }

}
