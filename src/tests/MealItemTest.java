package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class MealItemTest extends BasicTest {

	@Test(priority = 5)
	public void addMealToCart() throws InterruptedException {
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		// Close Popup
		lpp.closePopup();

		// AddToCart
		mp.addMealToCart(5);
		String errorMessage = nsp.returnMessage();
		Assert.assertEquals(errorMessage, "The Following Errors Occurred:" + "\n" + "Please Select Location",
				"[ERROR] Message doesn't exist.");
		nsp.dissapearMessage();

		// Choose Location
		Thread.sleep(2000);
		lpp.setLocationName("Burlington - Vermont");
		Thread.sleep(5000);
		mp.addMealToCart(5);
		Thread.sleep(2000);

		String mealAdd = nsp.returnMessage();
		Assert.assertEquals(mealAdd, "Meal Added To Cart", "[ERROR] Meal is not added to cart.");
		Thread.sleep(2000);

	}

	@Test(priority = 10)
	public void addMealToFavorite() throws Exception {
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		

		// Close Popup
		lpp.closePopup();
		// AddMeal
		mp.addMealFavorite();
		Thread.sleep(1000);
		String LoginFirstPlease = nsp.returnMessage();
		Assert.assertEquals(LoginFirstPlease, "Please login first!", "[ERROR] Login Failed.");
		// LoginForm
		this.driver.get(baseURL + "/guest-user/login-form");
		lp.loginForm(demoEmail, demoPass);
		// AddMealAgain
		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mp.addMealFavorite();
		Thread.sleep(1000);
		// Check
		String addFavBtn = nsp.returnMessage();
		Assert.assertEquals(addFavBtn, "Product has been added to your favorites.",
				"[ERROR] Product is not added to favorite.");

	}

	@Test(priority = 15)
	public void clearCart() throws Exception {
		SoftAssert sa = new SoftAssert();
		// GotoMeals
		this.driver.get(baseURL + "/meals");

	
		// Set Location
		lpp.setLocationName("City Center - Albany");
		Thread.sleep(1000);
		// Import xlsx file
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell url1 = row.getCell(0);
			String url = url1.getStringCellValue();

			this.driver.get(url);
			Thread.sleep(1000);
			mp.addMealToCart(4);
			Thread.sleep(1000);
		}
		sa.assertAll();
		csp.clearAll();
		// Check EmptyCart
		String emptyCart = nsp.returnMessage();
		Assert.assertEquals(emptyCart, "All meals removed from Cart successfully", "[ERROR] Meals are not removed.");
		fis.close();
		wb.close();
	}

}
