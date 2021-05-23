package ru.stqa.pft.addressbook.tests.contacts.baseChecks;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      int randomInt = (int) Math.floor(Math.random() * 1000);
      app.contact().create(new ContactData()
              .withFirstName("Kazuto" + randomInt)
              .withLastName("Kazuto" + randomInt)
              .withNick("Kirito" + randomInt)
              .withTitle("STL Tester")
              .withCompany("RATH")
              .withAddress("221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135")
              .withAddAddress("446-1236, Katsuse, Fujimi-shi, Saitama")
              .withHomePhone("+(8184)-234-4165")
              .withMobile("+8182-949-7643")
              .withWorkPhone("+8184 234 3054")
              .withFax("+8184-234-3054")
              .withAddPhone("+(8182)949 76 43")
              .withEmail("Kazuto.Kirigaya" + randomInt + "@gomail.com")
              .withEmail2("Black.Swordsman" + randomInt + "@mail.com")
              .withEmail3("Kirito" + randomInt + "@mail.com"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactData() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[\\s\\-().]", "");
  }

  private String mergePhones(ContactData contact) {
    return Stream.of(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(), contact.getAddPhone())
            .filter((s) -> !s.equals(""))
            .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
