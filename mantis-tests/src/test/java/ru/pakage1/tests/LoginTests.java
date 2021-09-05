package ru.pakage1.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pakage1.appmanager.HttpSession;

import java.io.IOException;

public class LoginTests extends  TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator","root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));

    }
}
