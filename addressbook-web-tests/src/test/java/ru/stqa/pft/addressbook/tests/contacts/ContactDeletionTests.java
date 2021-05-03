package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactDeletionTests extends TestBase {

  @Test(testName = "Check delete contact")
  public void testContactDeletion() {
    if (! app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData("Kazuto", null, "Kirigaya", null,
              null, null, null, null,
              null, null, "+8184-234-3054", null,
              "Kazuto.Kirigaya@gomail.com", null, null,
              null, null), true);
      app.navigate().gotoHomePage();
    }
    int before = app.contact().getContactCount();
    app.contact().selectRandomContact();
    app.contact().deleteSelectedContact();
    app.contact().acceptAlertDelete();
    app.contact().checkDeleteMessage();
    app.navigate().gotoHomePage();
    int after = app.contact().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
