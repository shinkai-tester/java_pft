package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Random;

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
    type(By.name("photo"),contactData.getPhotoPath());
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
    String commonYear = contactData.generateYear();
    type(By.name("byear"), commonYear);
    type(By.name("ayear"), commonYear);
    selectRandomDay();
    selectRandomMonth();
    selectRandomGroup();
  }

  public void selectRandomGroup() {
    WebElement drpDwnList = find(By.name("new_group"));
    Select objSel = new Select(drpDwnList);
    List<WebElement> weblist = objSel.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSel.selectByIndex(iSelect);
  }

  public void selectRandomDay() {
    WebElement drpDwnListBday = find(By.name("bday"));
    WebElement drpDwnListAday = find(By.name("aday"));
    Select objSelBday = new Select(drpDwnListBday);
    Select objSelAday = new Select(drpDwnListAday);
    List<WebElement> weblist = objSelBday.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSelBday.selectByIndex(iSelect);
    objSelAday.selectByIndex(iSelect);
  }

  public void selectRandomMonth() {
    WebElement drpDwnListBmonth = find(By.name("bmonth"));
    WebElement drpDwnListAmonth = find(By.name("amonth"));
    Select objSelBmonth = new Select(drpDwnListBmonth);
    Select objSelAmonth = new Select(drpDwnListAmonth);
    List<WebElement> weblist = objSelBmonth.getOptions();
    int iCnt = weblist.size();
    Random num = new Random();
    int iSelect = num.nextInt(iCnt);
    objSelBmonth.selectByIndex(iSelect);
    objSelAmonth.selectByIndex(iSelect);
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[1]"));
  }
}
