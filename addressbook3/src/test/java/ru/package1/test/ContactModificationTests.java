package ru.package1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        //app.goTo().ContactPage();
        if (app.db().contacts().size() == 0) {
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
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.contact().selectByID(modifiedContact.getId());
        File photo = new File("src/test/resources/img2.png");
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("FirstName_222")
                .withLastName("LastName_222").withMiddleName("MiddleName_222").withTitle("title_222").withNickname("222")
                .withEmail("email@2222.ru").withMobileTelephone("+722222222222")
                .withWorkTelephone("work_222").withAddress("Москва 2222222")
                .withCompany("Company_222").withPhoto(photo);
        app.contact().modifyContact(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();
        
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        app.logout();
    }


}
