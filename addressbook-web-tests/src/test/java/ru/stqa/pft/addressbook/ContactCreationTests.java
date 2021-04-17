package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();;
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactCreation() throws Exception {
    wd.get("http://localhost:8080/addressbook/");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.id("LoginForm")).click();
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
    wd.findElement(By.linkText("add new")).click();
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys("Kazuto");
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys("Nope");
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys("Kirigaya");
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys("Kirito");
    wd.findElement(By.name("photo")).sendKeys(System.getProperty("user.dir") + "/src/test/resources/photo/kirito.jpg");
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys("STL Tester");
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys("RATH");
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys("221-1082, Kamishingashi, Kawagoe-shi, Saitama, 350-1135");
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys("+8182-949-7643");
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys("+8184-234-3054");
    wd.findElement(By.name("work")).click();
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys("+8184-234-3054");
    wd.findElement(By.name("fax")).click();
    wd.findElement(By.name("fax")).clear();
    wd.findElement(By.name("fax")).sendKeys("+8184-234-4165");
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys("Kazuto.Kirigaya@gomail.com");
    wd.findElement(By.name("email2")).click();
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys("Kirito@mail.com");
    wd.findElement(By.name("email3")).click();
    wd.findElement(By.name("email3")).clear();
    wd.findElement(By.name("email3")).sendKeys("Black.Swordsman@mail.com");
    wd.findElement(By.name("homepage")).click();
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys("http://52.68.96.58/");
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("7");
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("October");
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys("2008");
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("7");
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("October");
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys("2008");
    wd.findElement(By.name("new_group")).click();
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    wd.findElement(By.linkText("home page")).click();
    wd.findElement(By.linkText("Logout")).click();
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
