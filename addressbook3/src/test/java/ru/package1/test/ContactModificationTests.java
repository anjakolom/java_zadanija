package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase{
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

    @Test
    public void testContactModification(){

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        app.contact().selectByID(modifiedContact.getId());
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("FirstName_m").withLastName("LastName_m");
        app.contact().modifyContact(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());
        
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

        app.logout();
    }


}
