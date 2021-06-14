package ru.stqa.pft.addressbook.tests.groups.generationFromFiles;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.generators.GroupDataGenerator;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GroupDataProviders {
  @DataProvider
  public static Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[]{new GroupData().withName("group 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[]{new GroupData().withName("group 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromCsv() throws IOException {

    String numberOfGroups = System.getProperty("numGroups", "1");

    String[] arguments = new String[] {"-c", numberOfGroups, "-f", "src/test/resources/dataFiles/groups/groups.csv", "-d", "csv"};
    GroupDataGenerator.main(arguments);

    List<Object[]> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.csv"))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromXml() throws IOException {
    String numberOfGroups = System.getProperty("numGroups", "1");

    String[] arguments = new String[] {"-c", numberOfGroups, "-f", "src/test/resources/dataFiles/groups/groups.xml", "-d", "xml"};
    GroupDataGenerator.main(arguments);

    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.xml"));) {
      StringBuilder xml = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        xml.append(line);
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml.toString());
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromJson() throws IOException {

    String numberOfGroups = System.getProperty("numGroups", "1");

    String[] arguments = new String[] {"-c", numberOfGroups, "-f", "src/test/resources/dataFiles/groups/groups.json", "-d", "json"};
    GroupDataGenerator.main(arguments);

    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.json"))) {
      StringBuilder json = new StringBuilder();
      String line = reader.readLine();
      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json.toString(), new TypeToken<List<GroupData>>() {
      }.getType()); // =List<GroupData>.class
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }
}
