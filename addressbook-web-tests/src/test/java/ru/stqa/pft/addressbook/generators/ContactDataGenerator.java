package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator extends RandomContactData {
  @Parameter(names = "-c", description = "Number of the contacts")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file)); // -c 3 -f src/test/resources/dataFiles/contacts/contacts.csv -d csv
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file)); // -c 3 -f src/test/resources/dataFiles/contacts/contacts.json -d json
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(getRandomContact());
    }
    return contacts;
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstName(), contact.getMiddleName(), contact.getLastName(), contact.getNick(),
                contact.getPhoto(), contact.getTitle(), contact.getCompany(), contact.getAddress(), contact.getHomePhone(),
                contact.getMobile(), contact.getWorkPhone(), contact.getEmail(),
                contact.getBirthDay(), contact.getBirthMonth(), contact.getBirthYear(), contact.getNotes()));
      }
    }
  }


  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    JsonSerializer<File> serializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.getPath());
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(File.class, serializer)
            .setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }
}
