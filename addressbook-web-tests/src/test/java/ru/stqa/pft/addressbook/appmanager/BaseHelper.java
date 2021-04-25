package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void type(By locator, String text) {
    if (text != null) {
      String existingText = getWebElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  public WebElement getWebElement(By locator) {
    return wd.findElement(locator);
  }

  public List<WebElement> getElementList(By locator) {
    return wd.findElements(locator);
  }

  public void selectRandom(By by) {
    WebElement drpDwnList = getWebElement(by);
    Select objSel = new Select(drpDwnList);
    List<WebElement> weblist = objSel.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSel.selectByIndex(iSelect);
  }

  public void selectRandomFromList(By by) {
    List<WebElement> options = getElementList(by) ;
    Random random = new Random();
    int index = random.nextInt(options.size());
    options.get(index).click();
  }

  public String getFilePath(String source) {
    return System.getProperty("user.dir") + "\\src\\test\\resources\\" + source;
  }

  public void uploadFile(By by, String path) {
    getWebElement(by).sendKeys(path);
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
