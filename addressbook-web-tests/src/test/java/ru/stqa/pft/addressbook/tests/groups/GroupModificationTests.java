package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test(testName = "Check group update")
  public void testGroupModification() {
    Groups before = app.group().all();
    int randomInt = (int)Math.floor(Math.random()*1000);
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("testgroup" + randomInt).withHeader("header" + randomInt).withFooter("footer" + randomInt);
    app.group().modify(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}
