package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.navigate().gotoGroupPage();
    app.group().selectGroup();
    app.group().deleteSelectedGroups();
    app.group().returnToGroupPage();
  }

}
