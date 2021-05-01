package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {
    app.navigate().gotoGroupPage();
    int before = app.group().getGroupCount();
    if (! app.group().isThereAGroup()) {
      app.group().createGroup(new GroupData("test1", null, null));
    }
    app.group().selectGroup(before - 1);
    app.group().initGroupModification();
    app.group().fillGroupForm(new GroupData("update1", "update2", "update3"));
    app.group().submitGroupModification();
    app.group().returnToGroupPage();
    int after = app.group().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
