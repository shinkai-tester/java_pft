package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification() {
    app.navigate().gotoGroupPage();
    app.group().selectGroup();
    app.group().initGroupModification();
    app.group().fillGroupForm(new GroupData("update1", "update2", "update3"));
    app.group().submitGroupModification();
    app.group().returnToGroupPage();
  }
}
