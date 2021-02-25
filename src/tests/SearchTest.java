package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	@Test
	public void searchResultsTest() throws Exception {
		SoftAssert sa = new SoftAssert();
		
		// GotoMeals and enter the location
		this.driver.get(baseURL + "/meals");
		lpp.closePopup();
		lpp.setLocationName("City Center - Albany");
		

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < 7; i++) {
			Thread.sleep(1000);
			XSSFRow row = sheet.getRow(i);

			String Location = row.getCell(0).getStringCellValue();
			String urlFromXslx = row.getCell(1).getStringCellValue();
			
			int resultCount = (int) row.getCell(2).getNumericCellValue();
		
	//		int realCount = rp.numberOfFoundMeals();
			
			
		this.driver.get(urlFromXslx);
		lpp.getSelectLocation();
		lpp.setLocationName(Location);

			Thread.sleep(4000);
			
			sa.assertEquals(rp.numberOfFoundMeals(), resultCount, "[ERROR] Number of results is not the same.");
		
			Thread.sleep(1000);

			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
				String realResult = rp.MealsName().get(j - 3);
				String result = row.getCell(j).getStringCellValue();
				sa.assertTrue(realResult.contains(result), "[ERROR] The order of the results is not the same.");
				Thread.sleep(2000);
			}
			sa.assertAll();
		}

		fis.close();
		wb.close();

	}
}
