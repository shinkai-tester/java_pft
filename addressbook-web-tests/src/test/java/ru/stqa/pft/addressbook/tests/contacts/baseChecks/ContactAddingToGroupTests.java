package ru.stqa.pft.addressbook.tests.contacts.baseChecks;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTests extends TestBase {
  ContactData usedContact;
  GroupData usedGroup;

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contactData = new ContactData()
            .withId(app.db().contactId())
            .withFirstName("Kyou")
            .withLastName("Souma");

    GroupData groupData = new GroupData()
            .withId(app.db().groupId())
            .withName("Cat");

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    if (contacts.isEmpty()) {
      app.db().addContact(contactData);
      usedContact = app.db().contacts().iterator().next();
    }

    if (groups.isEmpty()) {
      app.db().addGroup(groupData);
      usedGroup = app.db().groups().iterator().next();
    }

    if (!(groups.isEmpty() && contacts.isEmpty())) {
      for (ContactData c : contacts) {
        for (GroupData g : groups) {
          if (!c.getGroups().contains(g)) {
            usedContact = c;
            usedGroup = g;
            return;
          }
        }
      }

      usedContact = app.db().contacts().iterator().next();
      app.db().addGroup(groupData);
      usedGroup = groupData;
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    app.goTo().refreshPage();
    app.contact().addToGroup(usedContact, usedGroup);
    ContactData contactWithGroup = usedContact.inGroup(usedGroup);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(usedContact).withAdded(contactWithGroup)));
  }
}

