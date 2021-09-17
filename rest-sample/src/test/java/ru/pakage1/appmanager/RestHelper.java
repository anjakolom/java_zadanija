package ru.pakage1.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.json.TypeToken;
import org.testng.SkipException;
import ru.pakage1.tests.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {
    private  ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Boolean resolution = true;
        String json = getExecutor().execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
                .returnContent().asString();

        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issuesJson = parsed.getAsJsonObject().get("issues");
        Set<Issue> issues_set = new Gson().fromJson( issuesJson, new TypeToken<Set<Issue>>(){}.getType());
        Issue issue = issues_set.iterator().next();
        System.out.println(issue.toString());
        String stateName = issue.getState_name();
        System.out.println(stateName);

        if ((stateName.equals("Closed"))||(stateName.equals("Resolved"))||(stateName.equals("Deleted"))) {
             resolution = false;
        }
        return resolution;
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public Executor getExecutor(){
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
