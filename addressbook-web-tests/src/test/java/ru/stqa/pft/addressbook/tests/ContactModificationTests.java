package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.contact().editRandomContact();
    app.contact().fillContactForm(new ContactData("Yennefer", "Nope", "z Vengerbergu", "Yen",
            "yen.jpg", "Mage", "The Witcher 3", "Corvo Bianco", "+48 69 052 1343",
            "+48 69 052 1344", "+48 69 052 1345", "+48 69 052 1346",
            "Yennefer@gomail.com", "Yenna@mail.com", "Yennefer.Vengerberg@mail.com", "no"));
    app.contact().submitUpdate();
    app.contact().returnToHomePage();
  }
}
