package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.List;
import java.util.Random;

public class BaseHelper {
  protected WebDriver wd;
  private boolean acceptNextAlert = true;

  public BaseHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
    if (text != null) {
      String existingText = getElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public WebElement getElement(By locator) {
    return wd.findElement(locator);
  }

  public List<WebElement> getElementList(By locator) {
    return wd.findElements(locator);
  }

  public void selectRandom(By by) {
    Select objSel = getSelectObject(by);
    List<WebElement> weblist = objSel.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSel.selectByIndex(iSelect);
  }

  public List<WebElement> getSelectOptions(By locator) {
    Select select = getSelectObject(locator);
    return select.getOptions();
  }

  private Select getSelectObject(By locator) {
    return new Select(getElement(locator));
  }

  public String getFilePath(String source) {
    return System.getProperty("user.dir") + "\\src\\test\\resources\\" + source;
  }

  public void uploadFile(By by, String path) {
    File file = new File(path);
    if (file.exists()) {
      getElement(by).sendKeys(path);
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      getElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void verifyMessage(String expectedMessage) {
    String actualMessage = getElement(By.cssSelector("div.msgbox")).getText();
    Assert.assertTrue(actualMessage.contains(expectedMessage));
  }
}
