package ru.package1.test;

import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.package1.model.ContactData;
import ru.package1.model.Contacts;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroup extends TestBase {
    private SessionFactory sessionFactory;

    @Test
    public void testContactAddGroup() {
        int contactId = 0;
        String groupName = "New_groups_add";
        GroupData groupFound = new GroupData();

        //Проверка существования контакта, не привязанного ни к одной группе
        Contacts allContacts = app.db().contacts();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() == 0) {
                contactId = contact.getId();
                break;
            }
        }
        if (contactId == 0) {
            //Создание нового контакта без привязки к группе
            app.goTo().ContactPage();
            ContactData newContact = new ContactData().withLastName("Lastname_new")
                    .withFirstName("Firstname_new").withMiddleName("MidleName_new");
            app.contact().create(newContact, true);
            allContacts = app.db().contacts();
            contactId = allContacts.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get().getId();
        }
        //Проверим существование групп
        Groups allGroups = app.db().groups();
        if (allGroups.size() > 0) {
            groupFound = allGroups.iterator().next();

        }
        if (groupFound.getId() == 0) {
            //Создание новой группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName).withFooter("").withHeader(""));
            allGroups = app.db().groups();
            groupFound = allGroups.iterator().next();
            System.out.println("Новая группа: " + groupFound);

        }
        Assert.assertTrue(groupFound.getId() != 0 && contactId != 0);

        //Добавление контакта в группу
        app.goTo().ContactPage();
        app.contact().selectContactFlagByID(contactId);
        app.contact().addContactToGroup(groupFound);
        Contacts contact = app.db().contactsForID(contactId);
        System.out.println("Контакт: " + contact.iterator().next());
        System.out.println("Группы: " + contact.iterator().next().getGroups());
        Assert.assertTrue(contact.iterator().next().getGroups().size() == 1);

        //Проверка в БД привязки контакта к группе
        Groups groups = new Groups().withAdded(groupFound);
        assertThat(groups, equalTo(contact.iterator().next().getGroups()));

        app.logout();

    }
}
