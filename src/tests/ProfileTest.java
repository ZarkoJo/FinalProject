package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest{

	@Test(priority = 5)
	public void editProfile() throws Exception {
		this.driver.get(baseURL+"/guest-user/login-form");
		
		
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
		
		//Logout 
		ap.LogOutAccount();
		String logOutMessage = nsp.returnMessage();
		Assert.assertEquals(logOutMessage, "Logout Successfull!", "[ERROR] - Login failed");
		
	}
	
	@Test(priority=10)
	public void ChangeProfileImage() throws Exception {
		this.driver.get(baseURL+"/guest-user/login-form");
	
		
		//Close Popup (LocationPopupPage)
		lpp.closePopup();
		
		// Enter email/password/login (LoginPage)
		lp.loginForm(demoEmail, demoPass);
		String loginMessage = nsp.returnMessage();
		Assert.assertEquals(loginMessage, "Login Successfull", "[ERROR] Login Failed");
		
		this.driver.navigate().to(baseURL+"/member/profile");
		
		//Upload image
		pp.uploadImage("img/slika1.png");
		String succesfullUpload = nsp.returnMessage();
		Assert.assertEquals(succesfullUpload, "Profile Image Uploaded Successfully", "[ERROR] Profile image didn't upload");
		nsp.dissapearMessage();
		
		//DeleteImage
		pp.deleteImage();
		String deleteImage = nsp.returnMessage();
		Assert.assertEquals(deleteImage, "Profile Image Deleted Successfully", "[ERROR] Profile image didnt delete.");
		nsp.dissapearMessage();

		//Logout 
		ap.LogOutAccount();
		String logOutMessage = nsp.returnMessage();
		Assert.assertEquals(logOutMessage, "Logout Successfull!", "[ERROR] - Login failed");
		
	}
	
	
	
	
}
