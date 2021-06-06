package ru.stqa.pft.rest.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

  @BeforeClass
  public void init() {
    RestAssured.authentication = app.restAssured().auth();
    RestAssured.filters(new RequestLoggingFilter());
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Test
  public void testCreateIssue() throws IOException {
    int randomInt = (int)Math.floor(Math.random()*100);
    Set<Issue> oldIssues = app.restAssured().getIssues();

    Issue newIssue = new Issue()
            .withSubject(String.format("ShuTest issue %s", randomInt))
            .withDescription(String.format("New ShuTest issue %s (Rest Assured)", randomInt));

    int issueId = app.restAssured().createIssue(newIssue);
    Set<Issue> newIssues = app.restAssured().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssues() {
    skipIfNotFixedRestAssured(1103);

    Set<Issue> issues = app.restAssured().getIssues();
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
