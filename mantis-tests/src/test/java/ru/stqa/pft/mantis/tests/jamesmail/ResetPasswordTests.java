package ru.stqa.pft.mantis.tests.jamesmail;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.models.UserData;
import ru.stqa.pft.mantis.models.Users;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  UserData usedUser;
  int randomInt = (int)Math.floor(Math.random()*1000);
  String newPassword = "Password" + randomInt;


  @BeforeMethod
  public void ensurePreconditions() throws MessagingException, IOException {

    UserData adminData = new UserData().withUsername(app.getProperty("web.adminLogin")).withEmail(app.getProperty("web.adminEmail"));
    Users users = app.db().users().without(adminData);

    if (users.isEmpty()) {
      usedUser = new UserData()
              .withUsername(String.format("user%s", randomInt))
              .withEmail(String.format("user%s@localhost", randomInt))
              .withPassword("password");
      app.james().createUser(usedUser.getUsername(), usedUser.getPassword());
      app.registration().start(usedUser.getUsername(), usedUser.getEmail());
      List<MailMessage> mailMessages = app.james().waitForMail(usedUser.getUsername(), usedUser.getPassword(), 60000);
      String confirmationLink = app.james().findConfirmationLink(mailMessages, usedUser.getEmail());
      app.registration().finish(confirmationLink, usedUser.getPassword());
      assertTrue(app.newSession().login(usedUser.getUsername(), usedUser.getPassword()));
    } else usedUser = users.iterator().next();
  }

  @Test(testName = "Admin resets password for the user -> user sets a new one (James mail server)")
  public void testResetPasswordAsAdmin() throws MessagingException, IOException {

    if (app.james().doesUserExist(usedUser.getUsername())) {
      app.james().deleteUser(usedUser.getUsername());
    }

    app.james().createUser(usedUser.getUsername(), newPassword);

    if (app.james().getAllMail(usedUser.getUsername(), newPassword).size() > 0) {
      app.james().drainEmail(usedUser.getUsername(), newPassword);
    }

    app.login().withAdminData();
    app.goTo().manageUsersPage();
    app.resetPwd().clickResetPwdBtn(usedUser.getUsername());
    List<MailMessage> mailMessages = app.james().waitForMail(usedUser.getUsername(), newPassword, 60000);
    String setPasswordLink = app.james().findConfirmationLink(mailMessages, usedUser.getEmail());
    app.resetPwd().finish(setPasswordLink, newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(usedUser.getUsername(), newPassword));
    assertTrue(session.isLoggedInAs(usedUser.getUsername()));
  }
}
