package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.initNewContact();
        app.fillContactForm(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"));
        app.submitContactCreation();

    }


}
