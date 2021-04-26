package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    if (! app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData("Kazuto", null, "Kirigaya", null,
              null, null, null, null,
              null, null, "+8184-234-3054", null,
              "Kazuto.Kirigaya@gomail.com", null, null,
              null, null), true);
      app.navigate().gotoHomePage();
    }
    app.contact().selectRandomContact();
    app.contact().deleteSelectedContact();
    app.contact().acceptAlertDelete();
    app.contact().checkDeleteMessage();
  }
}
