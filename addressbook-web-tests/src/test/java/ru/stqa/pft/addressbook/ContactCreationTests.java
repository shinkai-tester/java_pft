package ru.stqa.pft.addressbook;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  ChromeDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost:8080/addressbook/");
    login(new LoginData("admin", "secret"));
  }

  private void login(LoginData loginData) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(loginData.getUsername());
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(loginData.getPassword());
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Kazuto", "Nope", "Kirigaya", "Kirito",
            System.getProperty("user.dir") + "/src/test/resources/photo/kirito.jpg", "STL Tester",
            "RATH", "221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135", "+8182-949-7643",
            "+8184-234-3054", "+8184-234-3054", "+8184-234-4165", "Kazuto.Kirigaya@gomail.com",
            "Kirito@mail.com", "Black.Swordsman@mail.com", "http://52.68.96.58/"));
    submitContact();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContact() {
    wd.findElement(By.xpath("(//input[@name='submit'])[1]")).click();
  }

  private void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNick());
    wd.findElement(By.name("photo")).sendKeys(contactData.getPhotoPath());
    wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
    wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
    wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    wd.findElement(By.name("work")).sendKeys(contactData.getWorkPhone());
    wd.findElement(By.name("fax")).sendKeys(contactData.getFax());
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    wd.findElement(By.name("email2")).sendKeys(contactData.getEmail2());
    wd.findElement(By.name("email3")).sendKeys(contactData.getEmail3());
    wd.findElement(By.name("homepage")).sendKeys(contactData.getHomepage());
    String commonYear = contactData.generateYear();
    wd.findElement(By.name("byear")).sendKeys(commonYear);
    wd.findElement(By.name("ayear")).sendKeys(commonYear);
    selectRandomDay();
    selectRandomMonth();
    selectRandomGroup();
  }

  private void selectRandomGroup() {
    WebElement drpDwnList = wd.findElement(By.name("new_group"));
    Select objSel = new Select(drpDwnList);
    List<WebElement> weblist = objSel.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSel.selectByIndex(iSelect);
  }

  private void selectRandomDay() {
    WebElement drpDwnListBday = wd.findElement(By.name("bday"));
    WebElement drpDwnListAday = wd.findElement(By.name("aday"));
    Select objSelBday = new Select(drpDwnListBday);
    Select objSelAday = new Select(drpDwnListAday);
    List<WebElement> weblist = objSelBday.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSelBday.selectByIndex(iSelect);
    objSelAday.selectByIndex(iSelect);
  }

  private void selectRandomMonth() {
    WebElement drpDwnListBmonth = wd.findElement(By.name("bmonth"));
    WebElement drpDwnListAmonth = wd.findElement(By.name("amonth"));
    Select objSelBmonth = new Select(drpDwnListBmonth);
    Select objSelAmonth = new Select(drpDwnListAmonth);
    List<WebElement> weblist = objSelBmonth.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSelBmonth.selectByIndex(iSelect);
    objSelAmonth.selectByIndex(iSelect);
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
