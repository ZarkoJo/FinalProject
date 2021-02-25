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

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.ResultPage;


public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String baseURL = "http://demo.yo-meals.com";
	protected String demoEmail = "customer@dummyid.com";
	protected String demoPass = "12345678a";
	protected LocationPopupPage lpp;
	protected CartSummaryPage csp;
	protected AuthPage ap;
	protected LoginPage lp;
	protected MealPage mp;
	protected NotificationSystemPage nsp;
	protected ProfilePage pp;
	protected ResultPage rp;
	
	@BeforeClass
	public void setup() {
			System.setProperty("webdriver.chrome.driver", "driver_lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
			this.waiter = new WebDriverWait(driver, 30);
			this.driver.manage().window().maximize();
			this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			this.driver.manage().window().maximize();
			this.ap = new AuthPage(driver, waiter);
			this.csp = new CartSummaryPage(driver, waiter);
			this.lpp = new LocationPopupPage(driver, waiter);
			this.lp = new LoginPage(driver, waiter);
			this.nsp = new NotificationSystemPage(driver, waiter);
			this.pp = new ProfilePage(driver, waiter);
			this.rp = new ResultPage(driver, waiter);
			this.mp= new MealPage(driver, waiter);
			
}
	
	@AfterMethod

		public void afterTest(ITestResult result) throws Exception {
			String testTime = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
			if (result.getStatus() == ITestResult.FAILURE) {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File ss = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(ss, new File("screenshots/" + testTime));
			}

		this.driver.manage().deleteAllCookies();
		this.driver.navigate().refresh();
	}
	
	@AfterClass 
	public void Quit() {
		this.driver.quit();
	}
	
}