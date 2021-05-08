package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.navigate().gotoGroupPage();
    if (! app.group().isThereAGroup()) {
      app.group().createGroup(new GroupData("test" + (int)Math.floor(Math.random()*1000), null, null));
    }
  }

  @Test(testName = "Check group deletion")
  public void testGroupDeletion() {
    List<GroupData> before = app.group().getGroupList();
    app.group().selectGroup(before.size() - 1);
    app.group().deleteSelectedGroups();
    app.group().returnToGroupPage();
    List<GroupData> after = app.group().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
