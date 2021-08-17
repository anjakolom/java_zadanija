package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("New_groups_1"));
        app.goTo().ContactPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName")
                .withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address")
                .withMobileTelephone("+79260211966").withWork("Work").withEmail( "email").withBirthday("10")
                .withBmonth("november").withYear( "1982").withGroup("New_groups_1");
        app.contact().create(contact, true);
        app.goTo().ContactPage();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((g)->g.getId()).max().getAsInt()))));

        app.logout();

    }


}
