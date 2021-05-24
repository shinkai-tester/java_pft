package ru.stqa.pft.addressbook.tests.groups.dbtests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDBCreationTests extends TestBase {

  @Test(testName = "Check group creation: DB assert")
  public void dbTestGroupCreation() {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    GroupData group = new GroupData().withName("test1").withHeader("groupHeader1").withFooter("groupFooter1");
    app.group().create(group);
    assertThat(app.db().groups().size(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    verifyGroupsInUIandDB();
  }

  @Test(testName = "Unable to create a group with ' : DB assert")
  public void dbTestBadGroupCreation() {
    Groups before = app.db().groups();
    app.goTo().groupPage();
    GroupData group = new GroupData().withName("test'");
    app.group().create(group);
    assertThat(app.db().groups().size(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
    verifyGroupsInUIandDB();
  }
}
