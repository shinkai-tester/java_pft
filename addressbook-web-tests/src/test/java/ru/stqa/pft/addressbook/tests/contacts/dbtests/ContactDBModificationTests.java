package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDBModificationTests extends TestBase {
  @BeforeMethod
  public void ensureDBPreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Kazuto")
              .withLastName("Kazuto")
              .withTitle("STL Tester")
              .withCompany("RATH")
              .withAddress("221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135")
              .withWorkPhone("+8184-234-3054")
              .withEmail("Kazuto.Kirigaya@gomail.com"));
      app.goTo().homePage();
    }
  }

  @Test(testName = "Check update contact from home page: DB assert")
  public void testDBContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    int randomInt = (int) Math.floor(Math.random() * 1000);
    File photo = new File("src/test/resources/photos/yen.jpg");
    ContactData newData = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Yennefer" + randomInt)
            .withLastName("z Vengerbergu" + randomInt)
            .withMiddleName("Nope")
            .withNick("Yen" + randomInt)
            .withPhoto(photo)
            .withTitle("Mage")
            .withCompany("The Witcher 3")
            .withAddress("Corvo Bianco")
            .withHomePhone("+48 69 052 1343")
            .withMobile("+48 69 052 1344")
            .withWorkPhone("+48 69 052 1345")
            .withFax("+48 69 052 1346")
            .withEmail("Yennefer" + randomInt + "@gomail.com")
            .withEmail2("Yenna" + randomInt + "@mail.com")
            .withEmail3("Yennefer.Vengerberg" + randomInt + "@mail.com")
            .withBirthDay("15")
            .withBirthMonth("November")
            .withBirthYear("1992")
            .withAnniversaryDay("15")
            .withAnniversaryMonth("november")
            .withAnniversaryYear("2022");
    app.contact().initModification(modifiedContact.getId());
    app.contact().modify(newData);
    assertEquals(app.db().contacts().size(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(modifiedContact, newData)));
    verifyContactsInUIandDB();
  }

  @Test(testName = "Check update contact from the details page: DB assert")
  public void testDBContactModificationFromDetails() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    app.contact().viewDetails(modifiedContact.getId());
    int randomInt = (int) Math.floor(Math.random() * 1000);
    File photo = new File("src/test/resources/photos/yen.jpg");
    ContactData newData = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Yennefer" + randomInt)
            .withLastName("z Vengerbergu" + randomInt)
            .withMiddleName("Nope")
            .withNick("Yen" + randomInt)
            .withPhoto(photo)
            .withTitle("Mage")
            .withCompany("The Witcher 3")
            .withAddress("Corvo Bianco")
            .withHomePhone("+48 69 052 1343")
            .withMobile("+48 69 052 1344")
            .withWorkPhone("+48 69 052 1345")
            .withFax("+48 69 052 1346")
            .withEmail("Yennefer" + randomInt + "@gomail.com")
            .withEmail2("Yenna" + randomInt + "@mail.com")
            .withEmail3("Yennefer.Vengerberg" + randomInt + "@mail.com")
            .withBirthDay("15")
            .withBirthMonth("November")
            .withBirthYear("1992")
            .withAnniversaryDay("15")
            .withAnniversaryMonth("november")
            .withAnniversaryYear("2022");
    app.contact().initModificationFromDetails(modifiedContact.getId());
    app.contact().modify(newData);
    assertEquals(app.db().contacts().size(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withModified(modifiedContact, newData)));
    verifyContactsInUIandDB();
  }
}
