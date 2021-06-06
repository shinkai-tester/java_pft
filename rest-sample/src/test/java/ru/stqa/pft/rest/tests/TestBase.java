package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }


  boolean isIssueOpen(int issueId) throws IOException {
    Issue issue = app.rest().getIssueById(issueId).iterator().next();
    System.out.println("==================================== BUG =======================================");
    System.out.println(issue);
    List<String> fixedStatuses = Arrays.asList("Resolved", "Closed");
    return !fixedStatuses.contains(issue.getStatus());
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  boolean isIssueOpenRestAssured(int issueId) {
    Issue issue = app.restAssured().getIssueById(issueId).iterator().next();
    System.out.println("==================================== BUG =======================================");
    System.out.println(issue);
    List<String> fixedStatuses = Arrays.asList("Resolved", "Closed");
    return !fixedStatuses.contains(issue.getStatus());
  }

  public void skipIfNotFixedRestAssured(int issueId) {
    if (isIssueOpenRestAssured(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
