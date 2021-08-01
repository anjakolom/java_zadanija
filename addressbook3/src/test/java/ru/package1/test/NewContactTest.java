package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.GroupData;

public class NewContactTest extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
        app.getNavigationHelper().gotoGroupPage();
        app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Title", "Company", "Address", "+79260211966", "Work", "email", "10", "november", "1982", "New_groups_1"), true);
        app.getNavigationHelper().gotoContactPage();
        app.logout();

    }


}
