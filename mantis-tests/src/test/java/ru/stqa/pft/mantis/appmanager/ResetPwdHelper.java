package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPwdHelper extends HelperBase{

  public ResetPwdHelper(ApplicationManager app) {
    super(app);
  }

  private final By resetPwdButton = By.cssSelector(".btn[value='Reset Password']");

  public void userEditPage(String username) {
    click(By.linkText(username));
  }

  public void clickResetPwdBtn(String username) {
    userEditPage(username);
    click(resetPwdButton);
  }

  public void finish(String changePwdLink, String newPwd) {
    app.registration().finish(changePwdLink, newPwd);
  }
}
