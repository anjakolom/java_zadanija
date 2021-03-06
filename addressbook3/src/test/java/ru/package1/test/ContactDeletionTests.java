package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().ContactPage();
        if (app.db().contacts().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New_groups_1"));
            app.goTo().groupPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName")
                    .withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address")
                    .withMobileTelephone("+79260211966").withWork("Work").withEmail( "email").withBirthday("10")
                    .withBmonth("november").withYear( "1982"), true);
            //.withGroup("New_groups_1")
            app.goTo().ContactPage();
        }
    }

    @Test(enabled = false)
    //Удаление из формы редактирования контакта
    public void testContactDelete1() {
        int before = app.contact().count();
        app.contact().select(1);
        app.contact().delete();
        app.goTo().ContactPage();
        int after = app.contact().count();
        System.out.println("before " + before + "; after " + after);
        Assert.assertEquals(after, before - 1);
    }

    @Test
    //Удаление из списка
    public void testContactDelete2() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact)));

        app.logout();
    }


}
