package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(testName = "Check contact creation")
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    int randomInt = (int)Math.floor(Math.random()*1000);
    ContactData contact = new ContactData()
            .withFirstName("Kazuto" + randomInt)
            .withLastName("Kazuto" + randomInt)
            .withMiddleName("Nope")
            .withNick("Kirito" + randomInt)
            .withPhoto(new File("src/test/resources/photos/kirito.jpg"))
            .withTitle("STL Tester")
            .withCompany("RATH")
            .withAddress("221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135")
            .withHomePhone("+8184-234-4165")
            .withMobile("+8182-949-7643")
            .withWorkPhone("+8184-234-3054")
            .withFax("+8184-234-3054")
            .withEmail("Kazuto.Kirigaya" + randomInt + "@gomail.com")
            .withEmail2("Black.Swordsman" + randomInt + "@mail.com")
            .withEmail3("Kirito" + randomInt + "@mail.com")
            .withHomepage("http://52.68.96.58/")
            .withBirthDay("7")
            .withBirthMonth("October")
            .withBirthYear("2001")
            .withAnniversaryDay("7")
            .withAnniversaryMonth("October")
            .withAnniversaryYear("2021")
            .withAddAddress("446-1236, Katsuse, Fujimi-shi, Saitama")
            .withAddPhone("+(8182)949 76 43")
            .withNotes("I would rather trust and regret, than doubt and regret.");

    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }
}
