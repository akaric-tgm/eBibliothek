package readtastic_uat;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Logout extends TestCase{
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		Parameters.setUpBrowser();
		this.driver = Parameters.driver;
		this.baseUrl = Parameters.baseUrl;

	}

        @Test
	public void testLogout() throws Exception {
		driver.get(baseUrl + "/benutzer_einloggen.xhtml");
		driver.findElement(By.xpath("//*[contains(@id,'username')]")).clear();
		driver.findElement(By.xpath("//*[contains(@id,'username')]")).sendKeys("akaric");
		driver.findElement(By.xpath("//*[contains(@id,'password')]")).clear();
		driver.findElement(By.xpath("//*[contains(@id,'password')]")).sendKeys("passwort");
		driver.findElement(By.xpath("//*[contains(@id,'submit')]")).click();
                assertTrue(driver.getPageSource().contains("Eingeloggt als akaric"));
                
                driver.get(baseUrl + "/basic_design.xhtml");
                driver.findElement(By.xpath("//*[contains(@id,'submit')]")).click();
                assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/app/benutzer_einloggen.xhtml"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
