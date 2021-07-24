package ru.package1;

import org.testng.annotations.Test;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.initNewContact();
        app.fillContactForm(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"));
        app.submitContactCreation();

    }


}
