package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDBDeletionTests extends TestBase {

  @BeforeMethod
  public void dbEnsurePreconditions() {
    app.goTo().groupPage();
    GroupData newGroup = new GroupData().withName("test1");
    if (app.db().groups().size() == 0) {
      app.group().create(newGroup);
    }
  }

  @Test(testName = "Check group deletion: DB assert")
  public void dbTestGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertEquals(app.db().groups().size(), before.size() - 1);
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupsInUIandDB();
  }

  @Test(testName = "Check all groups deletion: DB assert")
  public void dbTestAllGroupsDeletion() {
    Groups before = app.db().groups();
    app.group().deleteAll();
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.empty()));
    verifyGroupsInUIandDB();
  }
}
