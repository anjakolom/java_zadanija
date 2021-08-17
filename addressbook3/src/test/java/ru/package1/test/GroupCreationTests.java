package ru.package1.test;

import org.testng.annotations.Test;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("New_groups_55").withHeader("1111");
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()+1));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.withAdded(group.withId(after.stream()
                .mapToInt((g)->g.getId()).max().getAsInt()))));

        app.logout();

    }

    @Test
    public void testBadGroupCreation() throws Exception {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("New_groups_55'").withHeader("1111");
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after,equalTo(before));

        app.logout();

    }


}
