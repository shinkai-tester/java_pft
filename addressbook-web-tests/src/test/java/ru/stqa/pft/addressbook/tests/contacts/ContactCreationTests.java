package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactCreationTests extends TestBase {

  @Test(testName = "Check contact creation")
  public void testContactCreation() {
    int before = app.contact().getContactCount();
    app.contact().initContactCreation();
    app.contact().createContact(new ContactData("Kazuto", "Nope", "Kirigaya", "Kirito",
            "kirito.jpg", "STL Tester", "RATH", "221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135",
            "+8182-949-7643", "+8184-234-3054", "+8184-234-3054", "+8184-234-4165",
            "Kazuto.Kirigaya@gomail.com", "Kirito@mail.com", "Black.Swordsman@mail.com",
            "http://52.68.96.58/", "test1"), true);
    app.navigate().gotoHomePage();
    int after = app.contact().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}