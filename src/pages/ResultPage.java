package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage extends BasicPage{

	public ResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public List<WebElement> searchResult() {
		return this.driver.findElements(By.xpath("//*[@class = 'product-name']/a"));
	}
	
	public List<String> MealsName() {
		List <String> meals = new ArrayList<String>();
		for (int i = 0; i < this.searchResult().size(); i++) {
			String result = searchResult().get(i).getText();
			meals.add(result);
		}
		return meals;
	}
	
	public int numberOfFoundMeals() {
		int count = this.searchResult().size();
		return count;
	}
	
}
