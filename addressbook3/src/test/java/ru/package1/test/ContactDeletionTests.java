package ru.package1.test;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    //Удаление из редактирования контакта
    public void testContactDelete1() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();

    }
    @Test
    //Удаление изсписка
    public void testContactDelete2() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().selectContactFlag();
        app.getContactHelper().deleteContactButton();
        app.getContactHelper().closeAlert();

    }
}
