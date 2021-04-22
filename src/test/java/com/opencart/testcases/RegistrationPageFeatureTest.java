package com.opencart.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Opencart.base.BaseTest;
import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ExcelUtil;

public class RegistrationPageFeatureTest extends BaseTest {


	@BeforeClass
	public void RegisterSetup() {
		regpage=loginpage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object [][] data=ExcelUtil.getTestData(Constants.Register_sheet);
		return data;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstname, String lastname, 
			String email, String telephone, String password,
			String subscrib) {
		regpage.accountRegistration(firstname, lastname, email, telephone, password, subscrib);
	}
}
