package com.qtr.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileHelper {
    private static final String currentDir = System.getProperty("user.dir");
    private static final String defaultPropertyFilePath = "\\src\\main\\resources\\";

    public static Properties getProperties(String propertyFileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(currentDir + defaultPropertyFilePath + propertyFileName + ".properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                    return prop;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}