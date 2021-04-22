package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
//@Listeners(ExtentReportListener.class)
@Epic("Epic :200 define login feature")
@Story("us-101 : about the login")
public class LoginFeatureTest extends BaseTest{
	/**
	 * There should not be any driver apis should be present in test class
	 * Assertions should not be in page class
	 * The test class should be focusing mainly on the behavior of the page class.
	 * Assertion is testng's responsibility.
	 * Inside the page the driver apis should be called.
	 * 
	 */

	@Description("Login test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void testverifyTitle() {
		String gettitle=loginpage.gettitle();
		System.out.println("login : "+" "+gettitle);
		Assert.assertEquals(gettitle, Constants.Login_Title);
	}
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void testifSignuplinkexists() {
			Assert.assertTrue(loginpage.isSingnupLinkExist());
	}
	
	@Test(priority = 3)
	public void verifyloginBanner() {
		String banner=loginpage.getLoginBanner();
		System.out.println("The banner is : "+banner);
	}
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void testLogin() {
		loginpage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
}
