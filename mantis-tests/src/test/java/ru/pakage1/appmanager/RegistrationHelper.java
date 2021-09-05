package ru.pakage1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{
    //private final ApplicationManager app;
   // private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
      //  wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup.php");
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value="Зарегистрироваться"]"));

    }
}
