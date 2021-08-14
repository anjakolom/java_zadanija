package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoContactPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().GroupPage();
            app.group().create(new GroupData("New_groups_1", "1111", null));
            app.goTo().GroupPage();
            app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"), true);
            app.goTo().gotoContactPage();
        }
    }

    @Test(enabled = false)
    //Удаление из формы редактирования контакта
    public void testContactDelete1() {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().deleteContact();
        app.goTo().gotoContactPage();
        int after = app.getContactHelper().getContactCount();
        System.out.println("before " + before + "; after " + after);
        Assert.assertEquals(after, before - 1);
    }

    @Test
    //Удаление из списка
    public void testContactDelete2() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().selectContactFlag(index);
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().closeAlert();
        app.goTo().gotoContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        System.out.println("Сравнение списков контактов:");
        for (int i = 0; i < after.size(); i++) {
            System.out.println(before.get(i) + "; " + after.get(i));
        }
        Assert.assertEquals(before, after);
        app.logout();
    }
}
