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
        int contactId =0;
        int groupId = 0;
        String groupName = "New_groups_add";

        //Проверка существования контакта, привязанного к одной группе
        Contacts allContacts = app.db().contacts();
        for ( ContactData contact :  allContacts ) {
            if (contact.getGroups().size() == 0){
            contactId  = contact.getId();
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
        if (allGroups.size()>0){
            groupId = allGroups.iterator().next().getId();
            groupName = allGroups.iterator().next().getName();
        }
        if (groupId == 0) {
            //Создание новой группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(groupName).withFooter("").withHeader(""));
            allGroups = app.db().groups();
            groupId = allGroups.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get().getId();
            System.out.println("Новый ID группы: " + groupId);
        }
        Assert.assertTrue(groupId != 0 && contactId != 0);
        if (groupId != 0 && contactId != 0) {
            //Добавление контакта в группу
            app.goTo().ContactPage();
            app.contact().selectContactFlagByID(contactId);
            app.contact().addContactToGroup(groupId);
            Contacts contact = app.db().contactsForID(contactId);
            System.out.println("Контакт: " + contact.iterator().next());
            System.out.println("Группы: " + contact.iterator().next().getGroups());
            Assert.assertTrue(contact.iterator().next().getGroups().size() == 1);

            //Проверка в БД привязки контакта к группе
            GroupData group = new GroupData().withId(groupId).withName(groupName).withHeader("").withFooter("");
            Groups groups = new Groups().withAdded(group);
            assertThat(groups, equalTo(contact.iterator().next().getGroups()));
        }
        app.logout();

    }
}
