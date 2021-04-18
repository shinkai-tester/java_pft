package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.navigate().gotoGroupPage();
    app.group().initGroupCreation();
    app.group().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.group().submitGroupCreation();
    app.group().returnToGroupPage();
  }
}