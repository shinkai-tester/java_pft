package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  private final By usernameLoc = By.id("username");
  private final By emailLoc = By.id("email-field");
  private final By signupLoc = By.cssSelector("input[type='submit']");
  private final By passwordLoc = By.id("password");
  private final By passwordConfirmLoc = By.id("password-confirm");
  private final By submitLoc = By.cssSelector("[class=submit-button] > button");

  public void start(String username, String email) {
    app.goTo().signupPage();
    type(usernameLoc, username);
    type(emailLoc, email);
    click(signupLoc);
  }

  public void finish(String confirmationLink, String password) {
    open(confirmationLink);
    type(passwordLoc, password);
    type(passwordConfirmLoc, password);
    click(submitLoc);
  }
}
