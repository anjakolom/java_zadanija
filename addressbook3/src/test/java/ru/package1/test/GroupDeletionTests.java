package ru.package1.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.package1.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("New_groups_1").withHeader("1111"));
            app.goTo().groupPage();
        }
    }
    @Test
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
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
