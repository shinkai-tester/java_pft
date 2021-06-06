package ru.stqa.pft.rest.appmanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

  private HttpClientHelper rest;
  public final Properties properties;
  private RestAssuredHelper restAssured;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public HttpClientHelper rest() {
    if (rest == null) {
      rest = new HttpClientHelper(this);
    }
    return rest;
  }

  public RestAssuredHelper restAssured() {
    if (restAssured == null) {
      restAssured = new RestAssuredHelper(this);
    }
    return restAssured;
  }
}
