package ru.pakage1.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.pakage1.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {

    //Драйвер браузера: BrowserType.FIREFOX,BrowserType.IE,BrowserType.CHROME
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_defaults_inc.php.bak" );
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_defaults_inc.php.bak", "config_defaults_inc.php");
        app.stop();

    }


}
