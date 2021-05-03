package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

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
    List<ContactData> before = app.contact().getContactList();
    app.contact().selectRandomContact();
    app.contact().deleteSelectedContact();
    app.contact().acceptAlertDelete();
    app.contact().checkDeleteMessage();
    app.navigate().gotoHomePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }
}
