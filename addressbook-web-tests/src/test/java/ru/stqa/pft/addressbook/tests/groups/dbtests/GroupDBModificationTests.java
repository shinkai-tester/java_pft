package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDBModificationTests extends TestBase {

  @BeforeMethod
  public void dbEnsurePreconditions() {
    app.goTo().groupPage();
    GroupData newGroup = new GroupData().withName("test1").withHeader("header1").withFooter("footer1");
    if (app.db().groups().size() == 0) {
      app.group().create(newGroup);
    }
  }

  @Test(testName = "Check group update: DB assert")
  public void dbTestGroupModification() {
    Groups before = app.db().groups();
    int randomInt = (int) Math.floor(Math.random() * 1000);
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("testgroup" + randomInt).withHeader("header" + randomInt).withFooter("footer" + randomInt);
    app.group().modify(group);
    assertEquals(app.db().groups().size(), before.size());
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupsInUIandDB();
  }
}
