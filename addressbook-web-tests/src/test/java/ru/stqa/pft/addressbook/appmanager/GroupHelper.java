package ru.stqa.pft.addressbook.appmanager;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  @Step("Возвращаюсь на страницу со списком групп")
  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  @Step("Подтверждаю создание группы")
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  @Step("Заполняю поля группы данными")
  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  @Step("Открываю страницу создания группы")
  public void initGroupCreation() {
    click(By.name("new"));
  }

  @Step("Подтверждаю удаление группы")
  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  @Step("Выбираю группу по идентификатору")
  public void selectGroupById(int id) {
    getElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  @Step("Открываю страницу редактирования группы")
  public void initGroupModification() {
    click(By.name("edit"));
  }

  @Step("Подтверждаю все изменения группы")
  public void submitGroupModification() {
    click(By.name("update"));
  }

  @Step("Создаю группу")
  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    verifyMessage("A new group has been entered into the address book.");
    returnToGroupPage();
  }

  @Step("Редактирую группу")
  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    verifyMessage("Group record has been updated.");
    returnToGroupPage();
  }

  @Step("Удаляю группу")
  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupPage();
  }

  @Step("Удаляю все группы")
  public void deleteAll() {
    selectAll();
    deleteSelectedGroups();
    groupCache = null;
    verifyMessage("Group has been removed.");
    returnToGroupPage();
  }

  @Step("Выбираю все группы")
  private void selectAll() {
    List<WebElement> checkboxes = getElementList(By.name("selected[]"));
    for (WebElement checkbox : checkboxes) {
      if (!checkbox.isSelected()) {
        checkbox.click();
      }
    }
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  @Step("Получаю актуальное количество групп")
  public int count() {
    return getElementList(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  @Step("Получаю актуальный список групп")
  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = getElementList(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }
}
