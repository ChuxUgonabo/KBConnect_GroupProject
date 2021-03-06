package test.com.kbconnect.boundary;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class RouteAddSeleniumTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "/home/alchemist/blackboxTesting/chromedriver");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void routeAddTest() {
		driver.get("http://localhost:8080/KBPayment_GroupProject/login.jsp");
		driver.manage().window().setSize(new Dimension(1366, 736));
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("alchemist");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("Alert Subscriptions")).click();
		driver.findElement(By.name("routeId")).click();

		driver.findElement(By.cssSelector(".form-control:nth-child(1)")).click();
		assertThat(driver.findElement(By.cssSelector(".alert")).getText(),
				CoreMatchers.containsString("You are now subscribed receive alerts for route"));
	}
}
