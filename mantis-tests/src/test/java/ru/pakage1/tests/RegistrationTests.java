package ru.pakage1.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pakage1.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testRegistration() throws IOException, MessagingException {
        String email = "user1@localhost.localdomain";
        app.registration().start("user1",email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        findConfirmationLink(mailMessages, email);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        return null;
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
