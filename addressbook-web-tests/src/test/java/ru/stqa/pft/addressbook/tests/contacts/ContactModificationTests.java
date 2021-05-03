package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactModificationTests extends TestBase {

  @Test(testName = "Check update contact")
  public void testContactModification() {
    if (! app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData("Kazuto", null, "Kirigaya", null,
              null, null, null, "221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135",
              null, null, "+8184-234-3054", null,
              "Kazuto.Kirigaya@gomail.com", null, null,
              null, null), true);
      app.navigate().gotoHomePage();
    }
    int before = app.contact().getContactCount();
    app.contact().editRandomContact();
    app.contact().fillContactForm(new ContactData("Yennefer", "Nope", "z Vengerbergu", "Yen",
            "yen.jpg", "Mage", "The Witcher 3", "Corvo Bianco", "+48 69 052 1343",
            "+48 69 052 1344", "+48 69 052 1345", "+48 69 052 1346", "Yennefer@gomail.com",
            "Yenna@mail.com", "Yennefer.Vengerberg@mail.com", "no", null), false);
    app.contact().submitUpdate();
    app.navigate().gotoHomePage();
    int after = app.contact().getContactCount();
    Assert.assertEquals(after, before);
  }
}
