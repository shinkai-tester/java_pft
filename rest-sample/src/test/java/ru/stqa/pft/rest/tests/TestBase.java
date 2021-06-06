package ru.stqa.pft.rest.tests;

import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }
}
