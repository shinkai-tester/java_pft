package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import org.apache.http.client.fluent.Executor;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

public class RestAssuredHelper {

  private final ApplicationManager app;

  public RestAssuredHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Issue> getIssues() {
    String url = app.getProperty("api.url");
    String json = RestAssured.get(url + "/issues.json").asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String url = app.getProperty("api.url");
    String json = RestAssured.given()
            .param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post(url + "/issues.json").asString();
    JsonElement parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public AuthenticationScheme auth() {
    String key = app.getProperty("api.key");
    return RestAssured.basic(key, "");
  }

  public Set<Issue> getIssueById(int issueId) {
    String url = app.getProperty("api.url");
    String json = RestAssured.get(url + "/issues/" + issueId + ".json").asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }
}
