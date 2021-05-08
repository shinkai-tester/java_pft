package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNick());
    uploadFile(By.name("photo"), getFilePath(contactData.getPhoto()));
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

    if (contactData.getGroup() != null) {
      if (creation) {
        List<WebElement> allOptions = getSelectOptions(By.name("new_group"));
        boolean found = allOptions.stream().anyMatch(allOption -> allOption.getText().equals(contactData.getGroup()));
        if(found) {
          new Select(getElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    String commonYear = generateYear();
    type(By.name("byear"), commonYear);
    type(By.name("ayear"), commonYear);
    selectRandomDay();
    selectRandomMonth();
  }

  public void updateContact(ContactData contact) {
    fillContactForm(contact, false);
    submitUpdate();
    verifyMessage("Address book updated");
  }

  public void selectRandomDay() {
    selectRandom(By.name("bday"));
    selectRandom(By.name("aday"));
  }

  public void selectRandomMonth() {
    selectRandom(By.name("bmonth"));
    selectRandom(By.name("amonth"));
  }

  public String generateYear() {
    int min = 1950;
    int max = 2000;
    int randomYear = (int) Math.floor(Math.random() * (max - min + 1) + min);
    return String.valueOf(randomYear);
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])[1]"));
  }

  public void selectRandomContact() {
    selectRandomFromList(By.xpath("//tbody//input[@type='checkbox']"));
  }

  public void selectContact(int index) {
    getElementList(By.cssSelector("#maintable [name='selected[]']")).get(index).click();
  }

  public void editRandomContact() {
    selectRandomFromList(By.cssSelector("#maintable a[href^='edit.php']"));
  }

  public void initContactModification(int index) {
    getElementList(By.cssSelector("#maintable a[href^='edit.php']")).get(index).click();
  }

  public void deleteContactFromList(int index) {
    selectContact(index);
    initContactDeletionHome();
    closeAlertAndGetItsText();
    verifyMessage("Record successful deleted");
  }

  private void initContactDeletionHome() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public void acceptAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void submitUpdate() {
    click(By.xpath("(//input[@name='update'])[1]"));
  }


  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitCreation();
    verifyMessage("Information entered into address book.");
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return getElementList(By.name("entry")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts= new ArrayList<>();
    List<WebElement> elements = getElementList(By.name("entry"));
    for (WebElement element: elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstName, lastName, address);
      contacts.add(contact);
    }
    return contacts;
  }
}
