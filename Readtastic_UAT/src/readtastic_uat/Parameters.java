package readtastic_uat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Parameters{
    public static final int FIREFOX = 0;
    public static final int INTERNET_EXPLORER = 1;
    public static final int CHROME = 2;

    public static final String LINUX = "linux";
    public static final String MAC = "mac";
    public static final String WINDOWS = "windows.exe";

    public static String os = LINUX; //HIER OS AENDERN
    public static int browser = FIREFOX; //HIER BROWSER AENDERN
    public static int port = 8080; //HIER PORT AENDERN

    public static String baseUrl = "http://localhost:"+port+"/app";

    public static WebDriver driver;

    public static void setUpBrowser() throws Exception {
        switch(browser){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case INTERNET_EXPLORER:
                if(os.equals(WINDOWS)){
                    System.setProperty("webdriver.ie.driver", "webdrivers/iedriver_"+os);
                    DesiredCapabilities caps = DesiredCapabilities.internetExplorer(); caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
                    driver = new InternetExplorerDriver(caps);
                }else{
                    System.err.println("Internet Explorer wird von dem gewählten Betriebssystem nicht unterstützt!");
                    System.exit(1);
                }

                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver_"+os);
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
