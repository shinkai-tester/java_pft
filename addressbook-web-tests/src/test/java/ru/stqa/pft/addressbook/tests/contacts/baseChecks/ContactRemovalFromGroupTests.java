package ru.stqa.pft.addressbook.tests.contacts.baseChecks;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovalFromGroupTests extends TestBase {

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
            .withName(String.format("The Best Group%d", (int) Math.floor(Math.random() * 1000)));

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    if (groups.isEmpty() & !(contacts.isEmpty())) {
      app.db().addGroup(groupData);
      usedGroup = groupData;
      usedContact = app.db().contacts().iterator().next();
    } else {
      if (!(groups.isEmpty()) & contacts.isEmpty()) {
        usedGroup = app.db().groups().iterator().next();
        usedContact = contactData;
        app.db().addContact(usedContact);
      } else {
        if (groups.isEmpty() & contacts.isEmpty()) {
          usedGroup = groupData;
          usedContact = contactData;
          app.db().addGroup(usedGroup);
          app.db().addContact(usedContact);
        } else {
          for (ContactData c : contacts) {
            if (!c.getGroups().isEmpty()) {
              usedContact = c;
              usedGroup = c.getGroups().iterator().next();
              return;
            }
          }
          usedGroup = app.db().groups().iterator().next();
          usedContact = app.db().contacts().iterator().next();
        }
      }
    }
    app.goTo().homePage();
    app.goTo().refreshPage();
    app.contact().addToGroup(usedContact, usedGroup);
  }

  @Test
  public void testRemoveContactFromGroup() {
    Contacts before = app.db().contacts();
    usedContact = before.stream().filter(c -> (c.getId() == usedContact.getId())).collect(Collectors.toSet()).iterator().next();
    Groups groupsBefore = usedContact.getGroups();
    app.goTo().homePage();
    app.goTo().refreshPage();
    app.contact().removeFromGroup(usedContact, usedGroup);
    Contacts after = app.db().contacts();
    usedContact = after.stream().filter(c -> (c.getId() == usedContact.getId())).collect(Collectors.toSet()).iterator().next();
    assertThat(usedContact.getGroups().size(), equalTo(groupsBefore.size() - 1));
    Groups groupsAfter = usedContact.getGroups();
    assertThat(groupsAfter, equalTo(groupsBefore.without(usedGroup)));
  }
}
