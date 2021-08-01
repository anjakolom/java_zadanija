package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().fillContactForm(new ContactData("FirstName_3", "MiddleName_3", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work_3", "email", "10", "november", "1982", null),false);
        app.getContactHelper().submitContactModification();
        app.logout();
    }
}
