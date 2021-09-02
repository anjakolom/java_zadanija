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

public class ContactDeleteGroup extends TestBase{
    private SessionFactory sessionFactory;

    @Test
    public void testContactDeleteGroup() {

        //Создание нового контакта без привязки к группе
        app.goTo().ContactPage();
        ContactData newContact = new ContactData().withLastName("Lastname_new")
                .withFirstName("Firstname_new").withMiddleName("MidleName_new");
        app.contact().create(newContact, true);
        Contacts allContacts = app.db().contacts();
        int contactId = allContacts.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get().getId();

        //Создание новой группы
        String groupName = "New_groups_add";
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(groupName).withFooter("").withHeader(""));
        Groups allGroups = app.db().groups();
        int groupId = allGroups.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get().getId();
        System.out.println("Новый ID группы: " + groupId);

        app.goTo().ContactPage();
        app.contact().selectContactFlagByID(contactId);
        //Добавление контакта в группу
        app.contact().addContactToGroup(groupId);
        Contacts contact = app.db().contactsForID(contactId);
        System.out.println("Контакт: " + contact.iterator().next());
        System.out.println("Группы: " + contact.iterator().next().getGroups());
        Assert.assertTrue(contact.iterator().next().getGroups().size() == 1);

        //Проверка в БД привязки контакта к группе
        GroupData group  = new GroupData().withId(groupId).withName(groupName).withHeader("").withFooter("");
        Groups groups = new Groups().withAdded(group);
        assertThat(groups, equalTo(contact.iterator().next().getGroups()));

        //Удаление контакта из группы
        app.goTo().ContactPage();
        app.contact().changesGroup(groupId, groupName);
        app.contact().selectContactFlagByID(contactId);
        app.contact().deleteContactGroup();

        //Проверка удаления контакта из группы по БД
        Contacts contact2 = app.db().contactsForID(contactId);
        Assert.assertTrue(contact2.iterator().next().getGroups().size() == 0);

        app.logout();

    }
}
