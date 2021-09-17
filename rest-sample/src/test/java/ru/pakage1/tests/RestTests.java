package ru.pakage1.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;



public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        app.rest().skipIfNotFixed(100);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue").withState_name("Open");
        int issueId = app.rest().createIssue(newIssue);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(newIssues, oldIssues);
    }


}
