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

  public void attach(By locator, File file) {
    if (file != null) {
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  public WebElement getElement(By locator) {
    return wd.findElement(locator);
  }

  public List<WebElement> getElementList(By locator) {
    return wd.findElements(locator);
  }

  public List<WebElement> getSelectOptions(By locator) {
    Select select = getSelectObject(locator);
    return select.getOptions();
  }

  private Select getSelectObject(By locator) {
    return new Select(getElement(locator));
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

  public void selectByValue(By by, String value) {
    if (value != null) {
      String existingValue = new Select(getElement(by)).getFirstSelectedOption().getAttribute("value");
      if (!value.equals(existingValue)) {
        new Select(getElement(by)).selectByValue(value);
      }
    }
  }

  public void selectByText(By by, String text) {
    if (text != null) {
      String existingText = new Select(getElement(by)).getFirstSelectedOption().getText();
      if (!text.equals(existingText)) {
        new Select(getElement(by)).selectByVisibleText(text);
      }
    }
  }

  public void refreshPage() {
    wd.navigate().refresh();
  }
}
