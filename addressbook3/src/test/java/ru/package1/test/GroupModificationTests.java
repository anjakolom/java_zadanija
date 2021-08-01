package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("New_groups_2", "222", "222"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();
        app.logout();
    }

}
