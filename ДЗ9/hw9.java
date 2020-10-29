{\rtf1\ansi\ansicpg1251\cocoartf1671
{\fonttbl\f0\fmodern\fcharset0 Courier;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;}
{\*\expandedcolortbl;;\cssrgb\c0\c0\c0;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\sl280\partightenfactor0

\f0\fs24 \cf2 \expnd0\expndtw0\kerning0
\outl0\strokewidth0 \strokec2 import org.junit.Test;\
import org.junit.Before;\
import org.junit.After;\
import static org.junit.Assert.*;\
import static org.hamcrest.CoreMatchers.is;\
import static org.hamcrest.core.IsNot.not;\
import org.openqa.selenium.By;\
import org.openqa.selenium.WebDriver;\
import org.openqa.selenium.firefox.FirefoxDriver;\
import org.openqa.selenium.chrome.ChromeDriver;\
import org.openqa.selenium.remote.RemoteWebDriver;\
import org.openqa.selenium.remote.DesiredCapabilities;\
import org.openqa.selenium.Dimension;\
import org.openqa.selenium.WebElement;\
import org.openqa.selenium.interactions.Actions;\
import org.openqa.selenium.support.ui.ExpectedConditions;\
import org.openqa.selenium.support.ui.WebDriverWait;\
import org.openqa.selenium.JavascriptExecutor;\
import org.openqa.selenium.Alert;\
import org.openqa.selenium.Keys;\
import java.util.*;\
import java.net.MalformedURLException;\
import java.net.URL;\
public class CreateNewWebsiteTest \{\
  private String siteTitle = "Homework Dolgosheev\'bb;\
  private String siteName = "homework-dolgosheev-test";\
  private String siteDescription = "This is homework from student Dolgosheev Dmitriy\'bb;\
\
  private WebDriver driver;\
  private Map<String, Object> vars;\
  JavascriptExecutor js;\
  @Before\
  public void setUp() \{\
    driver = new ChromeDriver();\
    js = (JavascriptExecutor) driver;\
    vars = new HashMap<String, Object>();\
  \}\
  @After\
  public void tearDown() \{\
    driver.quit();\
  \}\
  @Test\
  public void createnew() \{\
    driver.get("https://sites.google.com/classic");\
    driver.manage().window().setSize(new Dimension(1014, 762));\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 300);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\\'ownerCreateBtn\\']/div/div/div/div")));\
    \}\
\
    // open website creation form\
    driver.findElement(By.xpath("//div[@id=\\'ownerCreateBtn\\']/div/div/div/div")).click();\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-classic\\']/div")).click();\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 30);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id=\\'sites-create-site-select-template-radiogroup\\']/div[3]/a")));\
    \}\
\
    // change website template\
    driver.findElement(By.xpath("//td[@id=\\'sites-create-site-select-template-radiogroup\\']/div[3]/a")).click();\
    driver.switchTo().frame(3);\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 30);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@id=\\'gadget-entry-0\\']/td[2]/span[2]")));\
    \}\
    driver.findElement(By.xpath("//tr[@id=\\'gadget-entry-0\\']/td[2]/span[2]")).click();\
    driver.findElement(By.xpath("//div[@id=\\'sites-admin-settings-select\\']")).click();\
    driver.switchTo().defaultContent();\
\
    // change website title\
    driver.findElement(By.xpath("//input[@id=\\'siteTitle\\']")).click();\
    driver.findElement(By.xpath("//input[@id=\\'siteTitle\\']")).sendKeys(this.siteTitle);\
    driver.findElement(By.xpath("//input[@id=\\'siteTitle\\']")).sendKeys(Keys.ENTER);\
\
    // change website name\
    driver.findElement(By.xpath("//input[@id=\\'siteName\\']")).click();\
    driver.findElement(By.xpath("//input[@id=\\'siteName\\']")).sendKeys(this.siteName);\
    driver.findElement(By.xpath("//input[@id=\\'siteName\\']")).sendKeys(Keys.ENTER);\
\
    // change website theme\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-site-select-theme-header\\']")).click();\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-site-select-theme-content\\']/div[2]/label/div")).click();\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-site-select-theme-header\\']")).click();\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-site-more-options-header\\']")).click();\
\
    // change website description\
    driver.findElement(By.xpath("//input[@id=\\'settingsSiteDesc\\']")).click();\
    driver.findElement(By.xpath("//input[@id=\\'settingsSiteDesc\\']")).sendKeys(this.siteDescription);\
    driver.findElement(By.xpath("//input[@id=\\'settingsSiteDesc\\']")).sendKeys(Keys.ENTER);\
    driver.findElement(By.xpath("//div[@id=\\'sites-create-site-more-options-header\\']")).click();\
\
    // perform captcha\
    driver.switchTo().frame(1);\
    driver.findElement(By.xpath("//span[@id=\\'recaptcha-anchor\\']/div")).click();\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 180);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id=\\'recaptcha-anchor\\'][contains(@class,\\'recaptcha-checkbox-checked\\')]/div")));\
    \}\
    driver.switchTo().defaultContent();\
\
    // create website\
    driver.findElement(By.xpath("//div[@id=\\'createBtn\\']")).click();\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 30);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\\'sites-app-title\\']/span/span/span")));\
    \}\
\
    // go to main page\
    driver.findElement(By.xpath("//div[@id=\\'sites-app-title\\']/span/span/a/span")).click();\
    \{\
      WebDriverWait wait = new WebDriverWait(driver, 30);\
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\\'ownerCreateBtn\\']/div/div/div/div")));\
    \}\
  \}\
\}\
}