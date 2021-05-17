package ru.stqa.pft.addressbook.tests.contacts.generationFromFiles;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactDataProviders {

  @DataProvider
  public static Iterator<Object[]> contactsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/contacts/contacts.csv"));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData()
              .withFirstName(split[0])
              .withLastName(split[1])
              .withMiddleName(split[2])
              .withNick(split[3])
              .withPhoto(new File(split[4]))
              .withTitle(split[5])
              .withCompany(split[6])
              .withAddress(split[7])
              .withHomePhone(split[8])
              .withMobile(split[9])
              .withWorkPhone(split[10])
              .withEmail(split[11])
              .withBirthDay(split[12])
              .withBirthMonth(split[13])
              .withBirthYear(split[14])
              .withNotes(split[15])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public static Iterator<Object[]> contactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/contacts/contacts.json"));
    StringBuilder json = new StringBuilder();
    String line = reader.readLine();
    while (line != null) {
      json.append(line);
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }
}
