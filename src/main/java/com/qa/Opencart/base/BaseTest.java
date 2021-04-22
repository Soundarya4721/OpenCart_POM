package com.qa.Opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.qa.Opencart.pages.AccountPage;
import com.qa.Opencart.pages.HomePage;
import com.qa.Opencart.pages.LoginPage;
import com.qa.Opencart.pages.RegistrationPage;

public class BaseTest {
	
	public BasePage basepage;
	public Properties properties;
	public  WebDriver driver;
	public HomePage homepage;
	public LoginPage loginpage;
	public AccountPage accpage;
	public RegistrationPage regpage;
	/**
	 * We initialize the properties from the base page where the method is written we call it here.
	 * We call the driver from the base page.
	 * we get the url properties from the config.
	 * Create an object for the login page so that it can inherited to test classes(child class)
	 */
	@BeforeTest
	public void setUp() {
		basepage=new BasePage();
		properties=basepage.init_properties();
		String browser=properties.getProperty("browser");
		driver=basepage.init_driver(browser);
		driver=basepage.getUrl();
		loginpage=new LoginPage(driver);
	}
	
	/**
	 * CLoses the browser
	 */
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
