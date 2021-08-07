package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

public class ContactDeletionTests extends TestBase {
    @Test
    //Удаление из редактирования контакта
    public void testContactDelete1() {
        app.getNavigationHelper().gotoContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
            app.getNavigationHelper().gotoGroupPage();
            app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"), true);
            app.getNavigationHelper().gotoContactPage();
        }
            int before = app.getContactHelper().getContactCont();
            app.getContactHelper().selectContact();
            app.getContactHelper().deleteContact();
            app.getNavigationHelper().gotoContactPage();
            int after = app.getContactHelper().getContactCont();
            System.out.println("before "+before+"; after "+after);
            Assert.assertEquals(after,before-1);
    }

    @Test
    //Удаление из списка
    public void testContactDelete2() {
        app.getNavigationHelper().gotoContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
            app.getNavigationHelper().gotoGroupPage();
            app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"), true);
            app.getNavigationHelper().gotoContactPage();
        }
        int before = app.getContactHelper().getContactCont();
        app.getContactHelper().selectContactFlag();
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().closeAlert();
        app.getNavigationHelper().gotoContactPage();
        int after = app.getContactHelper().getContactCont();
        Assert.assertEquals(after,before-1);
        app.logout();
    }
}
