package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNick());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    selectByValue(By.name("bday"), contactData.getBirthDay());
    selectByValue(By.name("bmonth"), contactData.getBirthMonth());
    type(By.name("byear"), contactData.getBirthYear());
    selectByValue(By.name("aday"), contactData.getAnniversaryDay());
    selectByValue(By.name("amonth"), contactData.getAnniversaryMonth());
    type(By.name("ayear"), contactData.getAnniversaryYear());
    type(By.name("address2"), contactData.getAddress());
    type(By.name("phone2"), contactData.getAddPhone());
    type(By.name("notes"), contactData.getNotes());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        assertEquals(contactData.getGroups().size(), 1);
        GroupData group = contactData.getGroups().iterator().next();
        selectByText(By.name("new_group"), group.getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillForm(contact, true);
    submitCreation();
    contactCache = null;
    verifyMessage("Information entered into address book.");
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    fillForm(contact, false);
    submitUpdate();
    contactCache = null;
    verifyMessage("Address book updated");
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initDeletionHome();
    closeAlertAndGetItsText();
    contactCache = null;
    verifyMessage("Record successful deleted");
  }

  public void clearAll() {
    selectAll();
    initDeletionHome();
    closeAlertAndGetItsText();
    contactCache = null;
    verifyMessage("Record successful deleted");
  }

  public void selectAll() {
    click(By.id("MassCB"));
  }

  public void viewDetails(int id) {
    getElement(By.cssSelector("#maintable a[href^='view.php?id=" + id + "']")).click();
  }

  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectByText(By.name("to_group"), group.getName());
    click(By.name("add"));
    contactCache = null;
    verifyMessage("Users added.");
    returnToGroupPage();
    clearGroupFilter();
  }

  public void removeFromGroup(ContactData contact, GroupData group) {
    selectByText(By.name("group"), group.getName());
    selectContactById(contact.getId());
    click(By.name("remove"));
    contactCache = null;
    verifyMessage("Users removed.");
    returnToGroupPage();
    clearGroupFilter();
  }

  private void clearGroupFilter() {
    selectByText(By.name("group"), "[all]");
  }

  public void returnToHomePage() {
    click(By.cssSelector("#content a[href='index.php']"));
  }

  private void returnToGroupPage() {
    click(By.cssSelector("#content a[href^='./?group=']"));
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])[1]"));
  }

  public void selectContactById(int id) {
    getElement(By.cssSelector("input[type='checkbox'][value='" + id + "']")).click();
  }

  public void initModification(int id) {
    getElement(By.cssSelector("#maintable a[href='edit.php?id=" + id + "']")).click();
  }

  public void initModificationFromDetails(int id) {
    getElement(By.name("modifiy")).click();
  }

  private void initDeletionHome() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public void submitUpdate() {
    click(By.xpath("(//input[@name='update'])[1]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return getElementList(By.name("entry")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = getElementList(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModification(contact.getId());
    String firstname = getElement(By.name("firstname")).getAttribute("value");
    String lastname = getElement(By.name("lastname")).getAttribute("value");
    String home = getElement(By.name("home")).getAttribute("value");
    String mobile = getElement(By.name("mobile")).getAttribute("value");
    String work = getElement(By.name("work")).getAttribute("value");
    String addPhone = getElement(By.name("phone2")).getAttribute("value");
    String address = getElement(By.name("address")).getAttribute("value");
    String email = getElement(By.name("email")).getAttribute("value");
    String email2 = getElement(By.name("email2")).getAttribute("value");
    String email3 = getElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstname)
            .withLastName(lastname)
            .withHomePhone(home)
            .withMobile(mobile)
            .withWorkPhone(work)
            .withAddPhone(addPhone)
            .withAddress(address)
            .withEmail(email)
            .withEmail2(email2)
            .withEmail3(email3);
  }
}
