package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/config/%s.properties", target))));
    String baseUrl = properties.getProperty("web.baseUrl");
    String seleniumGrid = properties.getProperty("selenium.server");
    String adminLogin = properties.getProperty("web.adminLogin");
    String adminPassword = properties.getProperty("web.adminPassword");
    String geckoDriverPath = properties.getProperty("web.geckoDriverPath");
    String chromeDriverPath = properties.getProperty("web.chromeDriverPath");
    String ieDriverPath = properties.getProperty("web.ieDriverPath");

    dbHelper = new DbHelper();

    if ("".equals(seleniumGrid)) {
      switch (browser) {
        case BrowserType.CHROME:
          setDriverPath("webdriver.chrome.driver", chromeDriverPath);
          wd = new ChromeDriver();
          break;
        case BrowserType.FIREFOX:
          setDriverPath("webdriver.gecko.driver", geckoDriverPath);
          wd = new FirefoxDriver();
          break;
        case BrowserType.IE:
          setDriverPath("webdriver.ie.driver", ieDriverPath);
          wd = new InternetExplorerDriver();
          break;
        default:
          throw new IOException("Unrecognized browser: " + browser);
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "WINDOWS")));
      wd = new RemoteWebDriver(new URL(seleniumGrid), capabilities);
    }

    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.get(baseUrl);
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(adminLogin, adminPassword);
  }

  private void setDriverPath(String driver, String path) {
    System.setProperty(String.format("webdriver.%s.driver", driver), path);
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }
}
