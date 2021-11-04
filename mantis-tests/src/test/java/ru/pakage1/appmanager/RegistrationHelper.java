package ru.pakage1.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pakage1.model.MailMessage;

import java.io.IOException;
import java.util.List;


public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);

    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//form[@id='account-update-form']/fieldset/span/button/span"));
    }

    public void login (String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        //click(By.cssSelector("input[value='Вход']"));
        click(By.xpath("//input[@type='submit']"));
        type(By.name("password"), password);
        //click(By.cssSelector("input[value='Вход']"));
        click(By.xpath("//input[@type='submit']"));
    }

    public void finishResetUserPassword (String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.cssSelector("#password"), password);
        type(By.cssSelector("#password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void resetPassword(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(username));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }

    public boolean isLoggedInAS(String username) throws IOException {
        return wd.findElement(By.xpath("//a[contains(text(),'administrator')]")).getText().equals(username);

    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
