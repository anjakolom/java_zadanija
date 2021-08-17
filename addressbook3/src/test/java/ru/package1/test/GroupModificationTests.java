package ru.package1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("New_groups_1"));
            app.goTo().groupPage();
        }
    }
    @Test
    public void testGroupModification(){

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("New_groups_3").withHeader("1111").withFooter("222");
        app.group().modify(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

        app.logout();
    }



}
