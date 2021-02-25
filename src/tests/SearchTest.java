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
		// GotoMeals
		this.driver.get(baseURL + "/meals");

		lpp.closePopup();
		// Set Location
		lpp.setLocationName("City Center - Albany");
		Thread.sleep(1000);

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(1000);
			XSSFRow row = sheet.getRow(i);

			lpp.selectLocation();
			String Location = row.getCell(0).getStringCellValue();
			lpp.setLocationName(Location);
			Thread.sleep(1000);

			String urlFromXslx = row.getCell(1).getStringCellValue();
			this.driver.get(urlFromXslx);

			int realCount = rp.numberOfFoundMeals();
			int resultCount = (int) row.getCell(2).getNumericCellValue();
			Assert.assertEquals(realCount, resultCount, "[ERROR] Number of results is not the same.");
			Thread.sleep(1000);

//			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
//				String realResult = rp.MealsName().get(j - 3);
//				String result = row.getCell(j).getStringCellValue();
//				sa.assertTrue(realResult.contains(result), "[ERROR] The order of the results is not the same.");
//				Thread.sleep(2000);
//			}
			sa.assertAll();
		}

		fis.close();
		wb.close();

	}
}
