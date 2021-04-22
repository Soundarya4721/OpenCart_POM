package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.utils.Constants;

public class HomePageFeatureTest extends BaseTest {

	@BeforeClass
	public void homepageSetup() {
		homepage=loginpage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyHomePagetitletest() {
		String actual=homepage.getHomepageTitle();
		Assert.assertEquals(actual, Constants.Home_Title);
	}
	
	@Test(priority = 2)
	public void verifyAccountTitle() {
		String actual=homepage.getAccounttitle();
		System.out.println(actual);
	}
	
	@Test(priority = 3)
	public void verifyMyaccountSectioncount() {
		Assert.assertTrue(homepage.getAccountSectionCount()==Constants.Account_sections);
	}
	
	@Test(priority = 4)
	public void verifyMyaccountList() {
		Assert.assertEquals(homepage.getAccountSectionList(), Constants.getAccountsList());
	}
	
	@Test(priority = 5)
	public void verifyEditAccountTest() {
		homepage.editaccount();
	}
}
