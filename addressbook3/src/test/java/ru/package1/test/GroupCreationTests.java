package ru.package1.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.package1.model.GroupData;
import ru.package1.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> List = new ArrayList<Object[]>();
        List.add(new Object[] {new GroupData().withName("Name1").withHeader("header1").withFooter("footer1")});
        List.add(new Object[] {new GroupData().withName("Name2").withHeader("header2").withFooter("footer2")});
        List.add(new Object[] {new GroupData().withName("Name3").withHeader("header3").withFooter("footer3")});
        return List.iterator();
    }
    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()+1));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.withAdded(group.withId(after.stream()
                .mapToInt((g)->g.getId()).max().getAsInt()))));

        app.logout();

    }


    @Test()
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
