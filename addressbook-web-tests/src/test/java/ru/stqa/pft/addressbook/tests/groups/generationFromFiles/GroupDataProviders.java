package ru.stqa.pft.addressbook.tests.groups.generationFromFiles;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
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
    list.add(new Object[]{ new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1") });
    list.add(new Object[]{ new GroupData().withName("group 2").withHeader("header 2").withFooter("footer 2") });
    list.add(new Object[]{ new GroupData().withName("group 3").withHeader("header 3").withFooter("footer 3") });
    return list.iterator();
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.csv"));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.xml"));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public static Iterator<Object[]> groupsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/dataFiles/groups/groups.json"));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); // =List<GroupData>.class
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }
}
