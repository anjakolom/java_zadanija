package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().modificationContactForm(new ContactData("FirstName_2", "MiddleName_2", "LastName_2", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"));
        app.getContactHelper().submitContactModification();
    }
}
