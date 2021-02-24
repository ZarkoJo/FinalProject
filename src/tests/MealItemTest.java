package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealItemTest extends BasicTest{

	@Test(priority = 5) 
	public void addMealToCart() throws InterruptedException {
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		LocationPopupPage lpp = new LocationPopupPage(driver, waiter);
		MealPage mp = new MealPage(driver, waiter);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, waiter);
		
		//Close Popup
		lpp.closePopup();
		
		//AddToCart
		mp.addMealToCart(5);
		String errorMessage = nsp.returnMessage();
		Assert.assertEquals(errorMessage, "The Following Errors Occurred:" + "\n" + "Please Select Location", "[ERROR] Message doesn't exist.");
		nsp.dissapearMessage();
		
		//Choose Location
		//lpp.selectLocation();
		Thread.sleep(2000);
		lpp.setLocationName("Burlington - Vermont");
		Thread.sleep(5000);
		mp.addMealToCart(5);
		Thread.sleep(2000);
		
		String mealAdd = nsp.returnMessage();
		Assert.assertEquals(mealAdd, "Meal Added To Cart", "[ERROR] Meal is not added to cart.");
		Thread.sleep(2000);

	}
	
}
