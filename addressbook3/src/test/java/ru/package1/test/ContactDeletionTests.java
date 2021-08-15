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
        app.goTo().ContactPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New_groups_1"));
            app.goTo().groupPage();
            app.contact().create(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"), true);
            app.goTo().ContactPage();
        }
    }

    @Test(enabled = false)
    //Удаление из формы редактирования контакта
    public void testContactDelete1() {
        int before = app.contact().getContactCount();
        app.contact().select(1);
        app.contact().delete();
        app.goTo().ContactPage();
        int after = app.contact().getContactCount();
        System.out.println("before " + before + "; after " + after);
        Assert.assertEquals(after, before - 1);
    }

    @Test
    //Удаление из списка
    public void testContactDelete2() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
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
