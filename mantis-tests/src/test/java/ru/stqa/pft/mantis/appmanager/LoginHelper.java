package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.models.LoginData;

public class LoginHelper extends HelperBase{
  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  private final By usernameLoc = By.id("username");
  private final By passwordLoc = By.id("password");
  private final By loginButton = By.cssSelector("input[type='submit']");



  public void withAdminData() {
    String admin = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    app.goTo().loginPage();
    login(new LoginData().withUsername(admin).withPassword(password));
  }

  private void login(LoginData loginData) {
    type(usernameLoc, loginData.getUsername());
    click(loginButton);
    type(passwordLoc, loginData.getPassword());
    click(loginButton);
  }
}
