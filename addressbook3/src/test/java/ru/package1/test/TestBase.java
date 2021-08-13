package ru.package1.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.package1.appmanager.ApplicationManager;

public class TestBase {
    //Драйвер браузера: BrowserType.FIREFOX,BrowserType.IE,BrowserType.CHROME
    protected static  ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        app.stop();

    }

}
