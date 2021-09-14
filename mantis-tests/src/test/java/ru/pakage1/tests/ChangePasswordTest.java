
package ru.pakage1.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pakage1.appmanager.HttpSession;
import ru.pakage1.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTest extends TestBase {

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String password_new = "password_new";
        String email = String.format("user%s@localhost", Long.toString(now));

        app.james().createUser(user, password);
        app.registration().start(user, email);

        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));
        app.james().drainEmail(user,password);

        System.out.println("Заходим под админом, отправляем письмо");
        //Через интерфейс
       // Assert.assertTrue(app.newSession().login("administrator", "root"));
        app.getDriver().get("http://localhost/mantisbt-2.25.2/login_page.php");
        app.getDriver().findElement(By.id("username")).clear();
        app.getDriver().findElement(By.id("username")).sendKeys("administrator");
        //app.getDriver().findElement(By.xpath("//input[@value='Вход']")).click();
        app.getDriver().findElement(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']")).click();
        app.getDriver().findElement(By.id("password")).clear();
        app.getDriver().findElement(By.id("password")).sendKeys("root");
        app.getDriver().findElement(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']")).click();
        //app.getDriver().findElement(By.xpath("//input[@value='Вход']")).click();
        //app.getDriver().findElement(By.cssSelector("i.fa.fa-gears.menu-icon")).click();
        //xpath=//div[@id='sidebar']/ul/li[6]/a/i
        app.getDriver().findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i")).click();

        //app.getDriver().findElement(By.linkText("Управление пользователями")).click();
        //xpath=//a[contains(@href, '/mantisbt-2.25.2/manage_user_page.php')]
        app.getDriver().findElement(By.xpath("//a[contains(@href, '/mantisbt-2.25.2/manage_user_page.php')]")).click();
        app.getDriver().findElement(By.linkText("user8")).click();
        //???app.getDriver().findElement(By.xpath("xpath=//form[@id='manage-user-reset-form']/fieldset/span/input")).click();
        app.getDriver().findElement(By.cssSelector("span.user-info")).click();
        app.getDriver().findElement(By.linkText("Выход")).click();
        //Читаем почту после получения ссылки
        mailMessages = app.james().waitForMail(user, password, 60000);
        confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password_new);
        Assert.assertTrue(app.newSession().login(user, password_new));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


}
