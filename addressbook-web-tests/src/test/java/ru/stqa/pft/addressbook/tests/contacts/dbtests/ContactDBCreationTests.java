package ru.stqa.pft.addressbook.tests.contacts.dbtests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;
import ru.stqa.pft.addressbook.tests.getDataFromDB.DbConnectionTest;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDBCreationTests extends TestBase {
  Logger logger = LoggerFactory.getLogger(DbConnectionTest.class);

  @Test(testName = "Check contact creation: DB assert")
  public void testDBContactCreation() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
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
    assertThat(app.db().contacts().size(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    verifyContactsInUIandDB();
    logger.info("Here is a list of the contacts from the DB before: \n");
    before.forEach(System.out::println);
    logger.info("Here is a list of the contacts from the DB after: \n");
    after.forEach(System.out::println);
  }
}
