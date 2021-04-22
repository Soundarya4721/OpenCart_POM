package com.opencart.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.utils.Constants;

import junit.framework.Assert;

public class AccountPageFeatureTest extends BaseTest {
	
	@BeforeClass
	public void AccountPageSetup() {
		
		homepage=loginpage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyEditAccountTest() {
		accpage=homepage.editaccount();
	}
	

	@Test(priority = 2)
	public void EditFields() {
		accpage.editfieldsLFnames(properties.getProperty("FirstName"),properties.getProperty("LastName"));
	}
	
	@Test(priority = 3)
	public void clickcntuebtn() {
		accpage.clickContinue();
	}
	

	@Test(priority = 4)
	public void verifysuccessBanner() {
		Assert.assertEquals(Constants.EditPage_Success_Msg, accpage.verifySuccessMsg());
	}
	

}
