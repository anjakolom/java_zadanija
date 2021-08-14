package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.goTo().groupPage();
        app.group().create(new GroupData("New_groups_1", "1111", null));
        app.goTo().ContactPage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("FirstName_n", "MiddleName_n", "LastName_n", "Nickname", "Title", "Company", "Address_new", "+7926021_new", "Work", "email_new", "10", "november", "1982", "New_groups_1");
        app.contact().create(contact, true);
        app.goTo().ContactPage();
        List<ContactData> after = app.contact().list();

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
        app.logout();

    }


}
