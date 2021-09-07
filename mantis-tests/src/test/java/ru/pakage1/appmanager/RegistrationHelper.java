package ru.pakage1.appmanager;

import org.openqa.selenium.By;


public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);

    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        int a = 1;
        click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//form[@id='account-update-form']/fieldset/span/button/span"));
    }
}
