package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{
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

    @Test
    public void testContactModification(){

        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().select(index);
        ContactData contact = new ContactData(before.get(index).getId(),"FirstName_m", null, "LastName_m",null, null, null, null, null, null, null, null, null, null, null);
        app.contact().modifyContact(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());
        
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

        app.logout();
    }


}
