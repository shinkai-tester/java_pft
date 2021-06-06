package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.models.Issue;
import ru.stqa.pft.mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    String adminLogin = app.getProperty("web.adminLogin");
    String adminPassword = app.getProperty("web.adminPassword");
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(adminLogin, adminPassword);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    String wsdl = app.getProperty("soap.wsdl");
    MantisConnectPortType mantisConnect = new MantisConnectLocator()
            .getMantisConnectPort(new URL(wsdl));
    return mantisConnect;
  }


  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    String adminLogin = app.getProperty("web.adminLogin");
    String adminPassword = app.getProperty("web.adminPassword");
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(adminLogin, adminPassword, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    ObjectRef project = new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName());
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(project);
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(adminLogin, adminPassword, issueData);
    IssueData createdIssueData = mc.mc_issue_get(adminLogin, adminPassword, issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }


}
