
package ru.pakage1.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pakage1.model.UserData;
import ru.pakage1.model.Users;
import ru.pakage1.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassword extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();

        //Выбрали первого попавшегося пользователя из базы
        Users allUsers = app.db().userlist();
        UserData changeUser = allUsers.iterator().next();
        String username = changeUser.getUsername();
        String email = changeUser.getEmail();

        //Залогинились админом
        app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        Assert.assertTrue(app.registration().isLoggedInAS(app.getProperty("web.adminLogin")));
        //Нажали кнопку сброса пароля для выбранного пользователя
        app.registration().resetPassword(username);

        //Получаем ссылку на изменение пароля из письма
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);

        //Изменение пароля
        String newpassword = String.format("password%s", now);
        app.registration().finish(confirmationLink, newpassword);

        //Проверка работы измененного пароля
        assertTrue(app.newSession().login(username, newpassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }


}
