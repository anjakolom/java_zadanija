package ru.package1;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {

        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();

    }


}
