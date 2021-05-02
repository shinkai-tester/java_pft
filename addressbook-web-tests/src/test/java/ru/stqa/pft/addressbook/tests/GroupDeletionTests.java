package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.navigate().gotoGroupPage();
    if (! app.group().isThereAGroup()) {
      app.group().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.group().getGroupList();
    app.group().selectGroup(before.size() - 1);
    app.group().deleteSelectedGroups();
    app.group().returnToGroupPage();
    List<GroupData> after = app.group().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}
