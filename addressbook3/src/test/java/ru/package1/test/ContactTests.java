package ru.package1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().ContactPage();
        if (!app.contact().isThereAContact()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New_groups_1"));
            app.goTo().groupPage();
            app.contact().create(new ContactData().withFirstName("FirstName").withMiddleName("MiddleName").withLastName("LastName")
                    .withNickname("Nickname").withTitle("Title").withCompany("Company").withAddress("Address, d.6, rv. 57 123234")
                    .withHomeTelephone("+8(495) 5555566").withMobileTelephone("+79260211966").withFaxTelephone("7-495-7774466").withWork("Work")
                    .withEmail( "email@email.ru").withBirthday("10")
                    .withBmonth("november").withYear( "1982").withGroup("New_groups_1"), true);
            app.goTo().ContactPage();
        }
    }
    @Test
    public void testContactPhones(){
        app.goTo().ContactPage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllemail(),equalTo(mergeEmail(contactInfoFromEditForm)));
        assertThat(mergeAddress(contact),equalTo(mergeAddress(contactInfoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomeTelephone(), contact.getMobileTelephone(), contact.getWorkTelephone())
                .stream().filter((s) -> ! s.equals("")).map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals("")).map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals("")).map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","")
                .replaceAll("[-()]","");
    }
}
