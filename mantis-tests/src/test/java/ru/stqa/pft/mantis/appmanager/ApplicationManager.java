package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private Properties properties;
  WebDriver wd;

  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/config/%s.properties", target))));

    switch (browser) {
      case BrowserType.CHROME:
        wd = new ChromeDriver();
        break;
      case BrowserType.FIREFOX:
        wd = new FirefoxDriver();
        break;
      case BrowserType.IE:
        wd = new InternetExplorerDriver();
        break;
    }
    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }

  public void stop() {
    wd.quit();
  }
}
