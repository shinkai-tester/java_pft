package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.navigate().gotoGroupPage();
    List<GroupData> before = app.group().getGroupList();
    app.group().createGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.group().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
