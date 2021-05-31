package ru.stqa.pft.mantis.tests.subethamail;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.tests.TestBase;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException {
    int randomInt = (int)Math.floor(Math.random()*1000);
    String email = String.format("user%s@localhost.com", randomInt);
    String user = String.format("user%s", randomInt);;
    String password = "Password1";
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
