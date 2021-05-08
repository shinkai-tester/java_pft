package ru.stqa.pft.addressbook.tests.groups;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test" + (int)Math.floor(Math.random()*1000), null, null));
    }
  }

  @Test(testName = "Check group update")
  public void testGroupModification() {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    int randomInt = (int)Math.floor(Math.random()*1000);
    GroupData group = new GroupData(before.get(index).getId(), "testgroup" + randomInt, "header" + randomInt, "footer" + randomInt);
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
