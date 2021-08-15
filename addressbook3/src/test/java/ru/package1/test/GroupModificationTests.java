package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.GroupData;

import java.util.Set;

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

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("New_groups_3").withHeader("1111").withFooter("222");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size());

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);
        app.logout();
    }



}
