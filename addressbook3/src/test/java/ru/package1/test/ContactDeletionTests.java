package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().ContactPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New_groups_1"));
            app.goTo().groupPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName")
                    .withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address")
                    .withMobileTelephone("+79260211966").withWork("Work").withEmail( "email").withBirthday("10")
                    .withBmonth("november").withYear( "1982").withGroup("New_groups_1"), true);
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
        Set<ContactData> before = app.contact().all();
        //int index = before.size() - 1;
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        System.out.println("Сравнение списков контактов:");

        Assert.assertEquals(before, after);
        app.logout();
    }


}
