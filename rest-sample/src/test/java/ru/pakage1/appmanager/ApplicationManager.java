package ru.pakage1.appmanager;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    WebDriver wd;
    private String browser;
    private RestHelper restHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }

    public RestHelper rest() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }

}
