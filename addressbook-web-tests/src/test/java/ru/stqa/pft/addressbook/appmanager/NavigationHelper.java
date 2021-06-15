package ru.stqa.pft.addressbook.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  @Step("Перехожу на страницу со списком групп")
  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && getElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      // делать ничего не надо
      return;
    }
    click(By.linkText("groups"));
  }

  @Step("Перехожу на страницу со списком контактов")
  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }
}
