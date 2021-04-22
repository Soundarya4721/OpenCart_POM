package com.qa.Opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.base.BasePage;
import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ElementUtil;

import io.qameta.allure.Step;

/**
 * 
 * @author ltatavarthy
 * having parent and child relationship i.e inheritence concept is applied with basepage
 * All the child pages have similar relationship with basepage
 * For every page class-By locators, constructors ,page actions...needs to be creators
 * By locators is private and page actions is public which is a concept called encapsulation.
 */
public class LoginPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elemutil;

	//By Locators : OR
	private By email_id=By.xpath("//input[@id='input-email']");
	private By password=By.xpath("//input[@id='input-password']");
	private By LoginBtn=By.xpath("//input[@value='Login'][@type='submit']");
	private By returningCust=By.xpath("(//div[@class='wel']/h2)[last()]");
	private By newCustomer=By.xpath("//div[@class='well']/child::h2[text()='New Customer']");
	private By RegisterLink=By.linkText("Register");
	
	//Constructor of the page class
	/**
	 * Use this driver in page actions(Features/ of page in form of methods)
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elemutil=new ElementUtil(driver);
	}
	
	/**
	 * 
	 * @return page title of the web page
	 */
	public String gettitle() {
		return elemutil.waitForTitlePresent(Constants.Login_Title, 10);
	}
	
	/**
	 * @return if the signup link is present or not
	 */
	public boolean isSingnupLinkExist() {
		//return driver.findElement(newCustomer).isDisplayed();
		return elemutil.doisDisplayed(newCustomer);
	}
	
	/**
	 * 
	 * @return text on the login section when it is a registered customer
	 * using elementUtil wrapper class we are class this method
	 */
	public String getLoginBanner() {
		String text=elemutil.dogetText(returningCust);//driver.findElement(returningCust).getText();
		System.out.println(text);
		return text;
	}
	
	/**
	 * 
	 * @param uname ---logs in with the user name
	 * @param pwd--logs in with the password
	 */
	@Step("Login with username : {0} and Password : {1}")
	public HomePage doLogin(String uname,String pwd) {
		System.out.println("Login in with : "+uname+","+pwd);
		/**driver.findElement(email_id).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(LoginBtn).click();**/
		
		elemutil.doSendKeys(email_id, uname);
		elemutil.doSendKeys(password, pwd);
		elemutil.doclick(LoginBtn);
		return new HomePage(driver);
	}
	
	public RegistrationPage navigateToRegister() {
		elemutil.doclick(RegisterLink);
		return new RegistrationPage(driver);
	}
}
