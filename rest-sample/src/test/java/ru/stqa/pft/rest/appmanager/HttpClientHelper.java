package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.models.Issue;

import java.io.IOException;
import java.util.Set;

public class HttpClientHelper {
  private final ApplicationManager app;

  public HttpClientHelper(ApplicationManager app) {
    this.app = app;
  }

  public Executor getExecutor() {
    String username = app.getProperty("api.key");
    return Executor.newInstance()
            .auth(username, "");
  }

  public Set<Issue> getIssues() throws IOException {
    String url = app.getProperty("api.url");
    String json = getExecutor().execute(Request.Get(url + "/issues.json"))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String url = app.getProperty("api.url");
    String json = getExecutor().execute(Request.Post(url + "/issues.json")
            .bodyForm
                    (new BasicNameValuePair("subject", newIssue.getSubject()),
                            new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssueById(int issueId) throws IOException {
    String url = app.getProperty("api.url");
    String json = getExecutor().execute(Request
            .Get(url + "/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() { }.getType());
  }
}
