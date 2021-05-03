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
      app.contact().createContact(new ContactData(
              "Kazuto",
              null,
              "Kirigaya",
              null,
              null,
              null,
              null,
              null,
              null,
              null,
              "+8184-234-3054",
              null,
              "Kazuto.Kirigaya@gomail.com",
              null,
              null,
              null,
              null));
      app.navigate().gotoHomePage();
    }
    List<ContactData> before = app.contact().getContactList();
    app.contact().deleteContactFromList(before.size() - 1);
    app.navigate().gotoHomePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }
}
