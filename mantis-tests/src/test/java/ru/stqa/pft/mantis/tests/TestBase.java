package ru.stqa.pft.mantis.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.models.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
    app.ftp().upload(new File("src/test/resources/config/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }


  boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    Issue issue = app.soap().getIssueById(issueId);
    System.out.println("==================================== ISSUE =======================================");
    System.out.println(issue);
    String status = issue.getStatus();
    String resolution = issue.getResolution();
    List<String> fixedStatuses = Arrays.asList("resolved", "closed");
    return !(fixedStatuses.contains(status) && resolution.contains("fixed"));
  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
