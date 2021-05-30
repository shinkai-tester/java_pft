package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperBase {

  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  public void open(String url) {
    wd.get(url);
  }

  public WebElement getElement(By locator) {
    return wd.findElement(locator);
  }

  public List<WebElement> getElements(By locator) {
    return wd.findElements(locator);
  }

  public void click(By locator) {
    getElement(locator).click();
  }

  public void type(By locator, String value) {
    click(locator);
    if (value != null) {
      String existingValue = getElement(locator).getAttribute("value");
      if (!value.equals(existingValue)) {
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
      }
    }
  }
}