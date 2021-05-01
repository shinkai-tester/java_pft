package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.navigate().gotoGroupPage();
    int before = app.group().getGroupCount();
    if (! app.group().isThereAGroup()) {
      app.group().createGroup(new GroupData("test1", null, null));
    }
    app.group().selectGroup(before - 1);
    app.group().deleteSelectedGroups();
    app.group().returnToGroupPage();
    int after = app.group().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}
