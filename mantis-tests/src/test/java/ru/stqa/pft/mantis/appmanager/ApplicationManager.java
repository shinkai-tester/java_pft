package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public final Properties properties;
  private WebDriver wd;

  private RegistrationHelper register;
  private FtpHelper ftp;
  private NavigationHelper nav;
  private MailHelper mail;
  private JamesHelper james;
  private LoginHelper login;
  private DbHelper db;
  private HttpSession http;
  private ResetPwdHelper resetPwd;
  private SoapHelper soap;

  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(String.format("src/test/resources/config/%s.properties", target)));
  }

  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public WebDriver getDriver() {
    String baseUrl = getProperty("web.baseUrl");
    String browser = getProperty("web.browser");
    String firefoxPath = getProperty("web.firefoxPath");
    String chromePath = getProperty("web.chromePath");
    String iePath = getProperty("web.iePath");

    if (wd == null) {
      if (browser != null && !browser.isEmpty()) {
        switch (browser) {
          case BrowserType.CHROME:
            setDriverPath("chrome", chromePath);
            wd = new ChromeDriver();
            break;
          case BrowserType.FIREFOX:
            setDriverPath("gecko", firefoxPath);
            wd = new FirefoxDriver();
            break;
          case BrowserType.IE:
            setDriverPath("ie", iePath);
            wd = new InternetExplorerDriver();
            break;
          default:
            throw new IllegalStateException("Unexpected browser: " + browser);
        }
      } else {
        throw new WebDriverException("Property 'web.browser' is null or not set");
      }

      wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

      if (baseUrl != null && !baseUrl.isEmpty()) {
        wd.get(baseUrl);
      } else {
        throw new WebDriverException(
                "Property 'web.baseUrl' is null or not set");
      }
    }
    return wd;
  }

  private void setDriverPath(String driver, String path) {
    System.setProperty(String.format("webdriver.%s.driver", driver), path);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (register == null) {
      register = new RegistrationHelper(this);
    }
    return register;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public NavigationHelper goTo() {
    if (nav == null) {
      nav = new NavigationHelper(this);
    }
    return nav;
  }

  public MailHelper mail() {
    if (mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

  public JamesHelper james() {
    if (james == null) {
      james = new JamesHelper(this);
    }
    return james;
  }

  public LoginHelper login() {
    if (login == null) {
      login = new LoginHelper(this);
    }
    return login;
  }

  public DbHelper db() {
    if (db == null) {
      db = new DbHelper(this);
    }
    return db;
  }

  public HttpSession newSession() {
    if (http == null)
      http = new HttpSession(this);
    return http;
  }

  public ResetPwdHelper resetPwd() {
    if (resetPwd == null)
      resetPwd = new ResetPwdHelper(this);
    return resetPwd;
  }

  public SoapHelper soap() {
    if (soap == null) {
      soap = new SoapHelper(this);
    }
    return soap;
  }
}
