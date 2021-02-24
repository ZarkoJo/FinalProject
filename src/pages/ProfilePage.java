package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement country = this.driver.findElement(By.name("user_country_id"));
		Select selectCountry = new Select(country);
		return selectCountry;
	}

	public Select getState() {
		WebElement state = this.driver.findElement(By.name("user_state_id"));
		Select selectState = new Select(state);
		return selectState;
	}

	public Select getCity() {
		WebElement city = this.driver.findElement(By.name("user_city"));
		Select selectCity = new Select(city);
		return selectCity;
	}

	public WebElement getSaveBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}

	public WebElement getUploadImageBtn() {
		return driver.findElement(By.className("ion-camera"));
	}

	public WebElement getDeleteImageBtn() {
		return this.driver.findElement(By.className("remove"));
	}
	
	public void uploadImage(String picture) throws Exception {
		js.executeScript("arguments[0].click();", this.getUploadImageBtn());
		WebElement uploadImg = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String imagePath = new File(picture).getCanonicalPath();
		uploadImg.sendKeys(imagePath); 
	}
	
	public void deleteImage() {
		js.executeScript("arguments[0].click();", this.getDeleteImageBtn());
	}
	
	public void updateProfile (String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws Exception {
		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhone().clear();
		this.getZipCode().clear();
		
		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(1000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(1000);
		this.getCity().selectByVisibleText(city);
		this.getSaveBtn().submit();
	}
	
}
