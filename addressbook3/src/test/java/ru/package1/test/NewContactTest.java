package ru.package1.test;

import com.solidfire.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test(dataProvider = "validContactFromJson")
    public void testNewContact(ContactData contact) throws Exception {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("New_groups_1"));
        app.goTo().ContactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/img.png");
        contact.withPhoto(photo);
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
