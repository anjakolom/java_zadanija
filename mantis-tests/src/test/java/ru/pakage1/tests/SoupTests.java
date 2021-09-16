package ru.pakage1.tests;

import org.testng.annotations.Test;
import ru.pakage1.model.Issue;
import ru.pakage1.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoupTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        app.soup().skipIfNotFixed(2);
        Set<Project> projects = app.soup().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        app.soup().skipIfNotFixed(1);
        Set<Project> projects = app.soup().getProjects();
        Issue issue = new Issue().withSummary("Test Issue")
                .withDescription("Test Issue description")
                .withName("Test new")
                .withProject(projects.iterator().next());
        Issue created = app.soup().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testisIssueOpen() throws RemoteException, ServiceException, MalformedURLException {
        app.soup().skipIfNotFixed(2);
        System.out.println(app.soup().isIssueOpen(1));
    }
}
