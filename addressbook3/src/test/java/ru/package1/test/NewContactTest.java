package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("New_groups_1"));
        app.goTo().ContactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/img.png");
        ContactData contact = new ContactData().withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName")
                .withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address dom 6, kv 8")
                .withHomeTelephone("+8(495) 5555566").withMobileTelephone("+79260211966").withFaxTelephone("7-495-7774466").withWork("Work")
                .withEmail( "email@email.ru").withBirthday("10").withBmonth("november").withYear( "1982").withGroup("New_groups_1").withPhoto(photo);
        app.contact().create(contact, true);
        app.goTo().ContactPage();
        assertThat(app.contact().count(),equalTo(before.size()+1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((g)->g.getId()).max().getAsInt()))));

        app.logout();

    }


    @Test(enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/img.jpeg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }





}
