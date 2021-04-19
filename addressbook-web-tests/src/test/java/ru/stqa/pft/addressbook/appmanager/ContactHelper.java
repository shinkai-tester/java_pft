package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(ChromeDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }
  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"),contactData.getMiddleName());
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
    String commonYear = generateYear();
    type(By.name("byear"), commonYear);
    type(By.name("ayear"), commonYear);
    selectRandomDay();
    selectRandomMonth();
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
    int randomYear = (int)Math.floor(Math.random()*(max-min+1)+min);
    return String.valueOf(randomYear);
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])[1]"));
  }

  public void selectRandomContact() {
    selectRandomFromList(By.xpath("//tbody//input[@type='checkbox']"));
  }

  public void editRandomContact() {
    selectRandomFromList(By.cssSelector("#maintable a[href^='edit.php']"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector("input[value='Delete']"));
  }

  public void acceptAlertDelete() {
    wd.switchTo().alert().accept();
  }

  public void checkDeleteMessage() {
    String expectedMessage = "Record successful deleted";
    String message = getWebElement(By.cssSelector("div.msgbox")).getText();
    Assert.assertEquals(expectedMessage, message);
  }

  public void submitUpdate() {
    click(By.xpath("(//input[@name='update'])[1]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
}
