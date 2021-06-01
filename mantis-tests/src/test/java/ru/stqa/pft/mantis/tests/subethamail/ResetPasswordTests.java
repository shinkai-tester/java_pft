package ru.stqa.pft.mantis.tests.subethamail;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.models.UserData;
import ru.stqa.pft.mantis.models.Users;
import ru.stqa.pft.mantis.tests.TestBase;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  UserData usedUser;
  int numEmails = 0;

  @BeforeMethod
  public void ensurePreconditions() throws IOException {

    app.mail().start();

    UserData adminData = new UserData().withUsername(app.getProperty("web.adminLogin")).withEmail(app.getProperty("web.adminEmail"));
    Users users = app.db().users().without(adminData);

    if (users.isEmpty()) {
      int randomInt = (int)Math.floor(Math.random()*1000);
      numEmails = 2;
      usedUser = new UserData()
              .withUsername(String.format("user%s", randomInt))
              .withEmail(String.format("user%s@localhost.com", randomInt))
              .withPassword("Password1");
      app.registration().start(usedUser.getUsername(), usedUser.getEmail());
      List<MailMessage> mailMessages = app.mail().waitForMail(numEmails, 10000);
      app.registration().finish(app.mail().findConfirmationLink(mailMessages, usedUser.getEmail()), usedUser.getPassword());
      assertTrue(app.newSession().login(usedUser.getUsername(), usedUser.getPassword()));
    } else usedUser = users.iterator().next();
  }

  @Test(testName = "Admin resets password for the user -> user sets a new one (mail server built into tests)")
  public void testResetPasswordAsAdmin() throws IOException {
    app.login().withAdminData();
    app.goTo().manageUsersPage();
    app.resetPwd().clickResetPwdBtn(usedUser.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(numEmails++, 10000);
    String setPasswordLink = app.mail().findLinkBySubject(mailMessages, usedUser.getEmail());
    String newPassword = "Password2";
    app.resetPwd().finish(setPasswordLink, newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(usedUser.getUsername(), newPassword));
    assertTrue(session.isLoggedInAs(usedUser.getUsername()));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
