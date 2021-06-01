package ru.stqa.pft.mantis.tests.jamesmail;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.tests.TestBase;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {


  @Test
  public void testRegistration() throws IOException, MessagingException {
    int randomInt = (int)Math.floor(Math.random()*1000);
    String email = String.format("user%s@localhost", randomInt);
    String user = String.format("user%s", randomInt);
    String password = "password";
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = app.james().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }
}
