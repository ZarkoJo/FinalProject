package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest{

	@Test(priority = 10)
	public void editProfile() throws Exception {
		this.driver.get(baseURL+"/guest-user/login-form");
		LocationPopupPage lpp = new LocationPopupPage(driver, waiter, js);
		LoginPage lp = new LoginPage(driver, waiter, js);
		NotificationSystemPage nsp = new NotificationSystemPage(driver, waiter, js);
		ProfilePage pp = new ProfilePage(driver, waiter, js);
		AuthPage ap = new AuthPage(driver, waiter, js);
		
		//Close Popup (LocationPopupPage)
		lpp.closePopup();
		
		// Enter email/password/login (LoginPage)
		lp.loginForm(demoEmail, demoPass);
		String loginMessage = nsp.returnMessage();
		Assert.assertEquals(loginMessage, "Login Successfull", "[ERROR] Login Failed");
		
		//Change all informations (ProfilePage)
		this.driver.get(baseURL+"/member/profile");
		pp.updateProfile("Zarko", "Jovanovic", "Vojvode Tankosica", "+3816254821", "18000", "United States", "Maryland", "Silver Spring");
		String setMessage = nsp.returnMessage();
		Assert.assertEquals(setMessage, "Setup Successful", "[ERROR] Profile didn't update");
	
		//Upload image
//		pp.uploadImage();
//		String succesfullUpload = nsp.returnMessage();
//		Assert.assertEquals(succesfullUpload, "Profile Image Uploaded Successfully", "[ERROR] Profile image didn't upload");
//		nsp.dissapearMessage();
	}
}
