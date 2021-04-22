package com.qa.Opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.base.BasePage;
import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ElementUtil;

public class RegistrationPage extends BasePage {
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	private By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");

	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value = 'Continue']");
	
	private By accountSuccessMessg = By.cssSelector("#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	ElementUtil elementUtil;


	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}


	public boolean accountRegistration(String firstname, String lastname, 
			String email, String telephone, String password,
			String subscribe) {

		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.email, email);//tom9898@gmail.com
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassword, password);

		if (subscribe.equals("yes")) {
			elementUtil.doclick(subscribeYes);
		} else {
			elementUtil.doclick(subscribeNo);
		}

		elementUtil.doclick(agreeCheckbox);
		elementUtil.doclick(continueButton);
		
		String text = elementUtil.dogetText(accountSuccessMessg);
		if(text.contains(Constants.ACCOUNT_SUCCESS_MESSG)) {
			elementUtil.doclick(logoutLink);
			elementUtil.doclick(registerLink);

			return true;
		}
		return false;
	}


}
