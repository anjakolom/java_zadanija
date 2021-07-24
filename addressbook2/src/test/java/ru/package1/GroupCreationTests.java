package ru.package1;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase  {


  @Test
  public void testGroupCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("New_groups_1", "1111", "222"));
    app.submitGroupCreation();
    app.returnToGroupPage();

  }


}
