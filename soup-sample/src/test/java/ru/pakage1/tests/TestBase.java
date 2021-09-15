package ru.pakage1.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.pakage1.appmanager.ApplicationManager;
//import sun.plugin2.util.BrowserType;

public class TestBase {



    //Драйвер браузера: BrowserType.FIREFOX,BrowserType.IE,BrowserType.CHROME
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }


}
