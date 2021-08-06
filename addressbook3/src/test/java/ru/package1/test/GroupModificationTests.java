package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.package1.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){

        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCont();
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
            app.getNavigationHelper().gotoGroupPage();
        }
        app.getGroupHelper().selectGroup(before-1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("New_groups_2", "222", "222"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCont();
        Assert.assertEquals(after,before);
        app.logout();
    }

}
