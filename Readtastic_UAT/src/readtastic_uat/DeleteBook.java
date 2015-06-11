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

public class DeleteBook extends TestCase{
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
	public void testNotBookDelete() throws Exception {
                driver.get(baseUrl + "/book_details.xhtml?book=2");
                driver.findElement(By.xpath("//*[contains(@id,'deletebook')]")).click();
                driver.findElement(By.xpath("//*[contains(@id,'no')]")).click();
                driver.get(baseUrl + "/index.xhtml");
                driver.findElement(By.xpath("//*[contains(@id,'2')]")).click();
                assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/app/book_details.xhtml?book=2"));
	}

        @Test
	public void testBookDelete() throws Exception {
                driver.get(baseUrl + "/book_details.xhtml?book=3");
                driver.findElement(By.xpath("//*[contains(@id,'deletebook')]")).click();
                driver.findElement(By.xpath("//*[contains(@id,'yes')]")).click();
                driver.get(baseUrl + "/index.xhtml");
                driver.findElement(By.xpath("//*[contains(@id,'3')]")).click();
                assertFalse(driver.getCurrentUrl().contains("/book_details.xhtml?book=3"));
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
