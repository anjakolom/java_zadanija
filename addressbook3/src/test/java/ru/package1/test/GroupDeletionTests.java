package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.package1.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("New_groups_1", "1111", null));
            app.getNavigationHelper().gotoGroupPage();
        }
    }
    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1;
        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(index);
        System.out.println("Сравнение списков групп:");
        for (int i = 0; i < after.size(); i++){
            System.out.println(before.get(i)+"; "+after.get(i));
        }
        Assert.assertEquals(before,after);
        app.logout();
    }


}
