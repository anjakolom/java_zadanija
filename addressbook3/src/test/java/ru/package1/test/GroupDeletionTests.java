package ru.package1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("New_groups_1").withHeader("1111"));
            app.goTo().groupPage();
        }
    }
    @Test
    public void testGroupDeletion() throws Exception {
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.without(deletedGroup)));
        app.logout();
    }




}
