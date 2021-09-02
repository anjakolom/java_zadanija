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

        int contactId =0;
        GroupData groupFound = new GroupData().withId(0);

        //Проверим существование групп
        Groups allGroups = app.db().groups();
        if (allGroups.size()>0){
            groupFound = allGroups.iterator().next();
        }
        if (groupFound.getId() == 0) {
            //Создание новой группы
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("New_groups_add").withFooter("").withHeader(""));
            allGroups = app.db().groups();
            groupFound = allGroups.iterator().next();
            System.out.println("Новая группа: " + groupFound);
        }
        Assert.assertTrue(groupFound.getId() != 0 );

        //Проверим существование контактов
        Contacts allContacts = app.db().contacts();
        if (allContacts.size() == 0) {
            //Создание нового контакта
            contactId = newContact("Firstname_new","Lastname_new","MiddleName_new");
            if (contactId !=0) {
                addToGroup(contactId, groupFound);
            }
        } else {
            //Проверка существования контакта, привязанного к одной группе
            for (ContactData contact : allContacts) {
                if (contact.getGroups().size() == 1) {
                    contactId = contact.getId();
                    groupFound = contact.getGroups().iterator().next();
                    break;
                }
            }
            //Если не нашелся контакт добавленный в одну группу, ищем контакт без группы
            if (contactId == 0) {
                for (ContactData contact : allContacts) {
                    if (contact.getGroups().size() == 0) {
                        contactId = contact.getId();
                        addToGroup(contactId, groupFound);
                        break;
                    }
                }
            }
            //Не нашлось подходящих контактов, создаем новый
            if (contactId == 0) {
                    contactId = newContact("Firstname_new","Lastname_new","MiddleName_new");
                    addToGroup(contactId, groupFound);
            }

        }
        Assert.assertTrue(contactId != 0 && groupFound.getId() != 0);

        //Удаление контакта из группы
        app.goTo().ContactPage();
        app.contact().changesGroup(groupFound.getId());
        app.contact().selectContactFlagByID(contactId);
        app.contact().deleteContactGroup();

        //Проверка удаления контакта из группы по БД
        Contacts contact2 = app.db().contactsForID(contactId);
        Assert.assertTrue(contact2.iterator().next().getGroups().size() == 0);
        System.out.println("Удаление для контакта: " + contact2.iterator().next());
        System.out.println("Из группы: " + groupFound.getId());
        app.logout();

    }

    private int newContact(String firstName, String lastName, String middleName) {
        int contactId;
        Contacts allContacts;
        ContactData newContact = new ContactData().withLastName(lastName)
                .withFirstName(firstName).withMiddleName(middleName);
        app.contact().create(newContact, true);
        allContacts = app.db().contacts();
        contactId = allContacts.stream().max((x, y) -> Integer.compare(x.getId(), y.getId())).get().getId();
        return contactId;
    }

    private void addToGroup(int contactId, GroupData group) {
        app.goTo().ContactPage();
        app.contact().selectContactFlagByID(contactId);
        app.contact().addContactToGroup(group);
        Contacts contact = app.db().contactsForID(contactId);
        Assert.assertTrue(contact.iterator().next().getGroups().size() == 1);

        //Проверка в БД привязки контакта к группе
        Groups groups = new Groups().withAdded(group);
        assertThat(groups, equalTo(contact.iterator().next().getGroups()));
        System.out.println("Добавление прошло для контакта: " + contact.iterator().next());
        System.out.println("В группу: " + contact.iterator().next().getGroups());
    }
}
