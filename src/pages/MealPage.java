package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}

	public WebElement getFavoriteBtn() {
		return driver.findElement(By.xpath("//*[@class = 'favourite  itemfav link']"));
	}

	public WebElement getQuantityInput() {
		return this.driver
				.findElement(By.xpath("//*[@id='body']/section[1]/div/div/div[2]/div/div[3]/div[1]/ul/li[3]/input"));
	}

	public WebElement getAddToCartBtn() {
		return driver.findElement(By.xpath("//*[@class = 'btn btn--primary btn--large js-proceedtoAddInCart ']"));
	}

	public void addMealToCart(String num) {
		String quantity = String.valueOf(num);
		this.getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.getQuantityInput().sendKeys(quantity);
		this.getAddToCartBtn().click();
	}

	public void addMealFavorite() {
		this.getFavoriteBtn().click();
	}

}
