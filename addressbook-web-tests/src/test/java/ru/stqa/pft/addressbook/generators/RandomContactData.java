package ru.stqa.pft.addressbook.generators;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

public class RandomContactData {

  Faker faker = new Faker();

  public ContactData getRandomContact() {
    String firstName = clean(faker.name().firstName());
    String middleName = clean(faker.name().nameWithMiddle().split(" ")[1]);
    String lastName = clean(faker.name().lastName());
    String nickname = clean(faker.pokemon().name());
    File photo = new File("src/test/resources/photos/" + getRandomPhoto());
    String jobTitle = faker.job().title();
    String company = clean(faker.company().name());
    String address = faker.address().fullAddress();
    String homePhone = faker.phoneNumber().phoneNumber();
    String mobile = faker.phoneNumber().cellPhone();
    String workPhone = faker.phoneNumber().phoneNumber();
    String email = firstName + "_" + lastName + "@gmail.com";
    LocalDate birthday = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int bDay = birthday.getDayOfMonth();
    String bMonth = StringUtils.capitalize(Month.of((birthday.getMonthValue())).name().toLowerCase());
    int bYear = birthday.getYear();
    String notes = clean(faker.backToTheFuture().quote());

    return new ContactData()
            .withFirstName(firstName)
            .withLastName(lastName)
            .withMiddleName(middleName)
            .withNick(nickname)
            .withPhoto(photo)
            .withTitle(jobTitle)
            .withCompany(company)
            .withAddress(address)
            .withHomePhone(homePhone)
            .withMobile(mobile)
            .withWorkPhone(workPhone)
            .withEmail(email)
            .withBirthDay(String.valueOf(bDay))
            .withBirthMonth(bMonth)
            .withBirthYear(String.valueOf(bYear))
            .withNotes(notes);
  }

  private String getRandomPhoto() {
    List<String> photos = Arrays.asList("cutie1.jpg", "cutie2.png", "cutie3.gif", "cutie4.jpg", "cutie5.jpg");
    int rnd = new Random().nextInt(photos.size());
    return photos.get(rnd);
  }

  private String clean(String text) {
    return text.replace("'", " ");
  }
}

