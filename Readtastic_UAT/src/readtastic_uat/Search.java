package readtastic_uat;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Search extends TestCase{
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		Parameters.setUpBrowser();
		this.driver = Parameters.driver;
		this.baseUrl = Parameters.baseUrl;
		driver.get(baseUrl + "/benutzer_einloggen.xhtml");
		driver.findElement(By.id("j_idt19:username")).clear();
		driver.findElement(By.id("j_idt19:username")).sendKeys("sbrinnich");
		driver.findElement(By.id("j_idt19:password")).clear();
		driver.findElement(By.id("j_idt19:password")).sendKeys("passwort");
		driver.findElement(By.id("j_idt19:submit")).click();

	}

	@Test
	public void testSearchBookByTitle() throws Exception {
		driver.get(baseUrl + "/index.xhtml");
		driver.findElement(By.id("j_idt18:search")).clear();
		driver.findElement(By.id("j_idt18:search")).sendKeys("Harry Potter 1");
		driver.findElement(By.id("j_idt18:submitSearch")).click();
		assertFalse(driver.getPageSource().contains("Harry Potter 2")); 
	}
        
        @Test
	public void testSearchBookByAuthor() throws Exception {
		driver.get(baseUrl + "/index.xhtml");
		driver.findElement(By.id("j_idt18:search")).clear();
		driver.findElement(By.id("j_idt18:search")).sendKeys("Joanne K. Rowling");
		driver.findElement(By.id("j_idt18:submitSearch")).click();
		assertTrue(driver.getPageSource().contains("Harry Potter 1")); 
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
