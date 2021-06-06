package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    int randomInt = (int)Math.floor(Math.random()*100);
    Set<Issue> oldIssues = app.rest().getIssues();

    Issue newIssue = new Issue()
            .withSubject(String.format("ShuTest issue %s", randomInt))
            .withDescription(String.format("New ShuTest issue %s", randomInt));

    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssues() throws IOException {
    skipIfNotFixed(1106);

    Set<Issue> issues = app.rest().getIssues();
    System.out.println("==================================== ISSUES =======================================");
    System.out.println("Issues number: " + issues.size());
    if (!issues.isEmpty()) {
      System.out.println("Issues:");
      for (Issue issue : issues) {
        System.out.println(issue);
      }
    }
  }
}
