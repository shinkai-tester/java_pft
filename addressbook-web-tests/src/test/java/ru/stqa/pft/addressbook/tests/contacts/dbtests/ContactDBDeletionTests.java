package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDBDeletionTests extends TestBase {
  @BeforeMethod
  public void ensureDBPreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Tooru")
              .withLastName("Honda"));
      app.goTo().homePage();
    }
  }

  @Test(testName = "Check delete contact: DB assert")
  public void testDBContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.db().contacts().size(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactsInUIandDB();
  }

  @Test(testName = "Check all contacts deletion: DB assert")
  public void testDBallContactsDeletion() {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    app.contact().clearAll();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.empty()));
    verifyContactsInUIandDB();
  }
}
