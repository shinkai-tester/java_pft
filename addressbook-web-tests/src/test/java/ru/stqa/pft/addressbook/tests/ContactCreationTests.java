package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.contact().initContactCreation();
    app.contact().fillContactForm(new ContactData("Kazuto", "Nope", "Kirigaya", "Kirito",
            System.getProperty("user.dir") + "\\src\\test\\resources\\photo\\kirito.jpg", "STL Tester",
            "RATH", "221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135", "+8182-949-7643",
            "+8184-234-3054", "+8184-234-3054", "+8184-234-4165", "Kazuto.Kirigaya@gomail.com",
            "Kirito@mail.com", "Black.Swordsman@mail.com", "http://52.68.96.58/"));
    app.contact().submitContact();
    app.navigate().gotoHomePage();
  }
}
