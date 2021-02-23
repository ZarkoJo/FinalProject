package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;


public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String baseURL = "http://demo.yo-meals.com/";
	protected String demoEmail = "customer@dummyid.com";
	protected String demoPass = "12345678a";

	
	@BeforeClass
	public void setup() {
			System.setProperty("webdriver.chrome.driver", "driver_lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
			this.waiter = new WebDriverWait(driver, 30);
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			this.driver.manage().window().maximize();
}
	
	@AfterMethod
	public void screenShot(ITestResult response) throws IOException {
		String shotTime= new SimpleDateFormat("'yyyyMMddHHmmss'.png'").format(new Date());
		if (ITestResult.FAILURE == response.getStatus()) {
				TakesScreenshot screenshot = (TakesScreenshot) this.driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src,
						new File("screenshots/"+ shotTime));
		}
		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
	}
	
	@AfterClass 
	public void Quit() {
		this.driver.quit();
	}
	
}