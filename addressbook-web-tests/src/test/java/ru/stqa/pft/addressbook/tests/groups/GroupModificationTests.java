package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test(testName = "Check group update")
  public void testGroupModification() {
    app.navigate().gotoGroupPage();
    if (! app.group().isThereAGroup()) {
      app.group().createGroup(new GroupData("test" + (int)Math.floor(Math.random()*1000), null, null));
    }
    List<GroupData> before = app.group().getGroupList();
    app.group().selectGroup(before.size() - 1);
    app.group().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "testgroup" + (int)Math.floor(Math.random()*1000), null, null);
    app.group().fillGroupForm(group);
    app.group().submitGroupModification();
    app.group().returnToGroupPage();
    List<GroupData> after = app.group().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
