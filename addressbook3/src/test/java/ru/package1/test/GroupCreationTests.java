package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.package1.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCont();
        app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCont();
        Assert.assertEquals(after,before+1);
        app.logout();

    }


}
