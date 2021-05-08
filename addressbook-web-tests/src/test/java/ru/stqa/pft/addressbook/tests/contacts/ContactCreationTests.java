package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(testName = "Check contact creation")
  public void testContactCreation() {
    List<ContactData> before = app.contact().getContactList();
    app.contact().initContactCreation();
    ContactData contact = new ContactData(
            "Kazuto",
            "Nope",
            "Kirigaya",
            "Kirito",
            "kirito.jpg",
            "STL Tester",
            "RATH",
            "221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135",
            "+8182-949-7643",
            "+8184-234-3054",
            "+8184-234-3054",
            "+8184-234-4165",
            "Kazuto.Kirigaya@gomail.com",
            "Kirito@mail.com",
            "Black.Swordsman@mail.com",
            "http://52.68.96.58/",
            "test1");
    app.contact().createContact(contact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
