package com.qa.Opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Opencart.base.BasePage;
/**
 * 
 * @author ltatavarthy
 * having parent and child relationship i.e inheritence concept is applied with basepage
 * All the child pages have similar relationship with basepage
 */
import com.qa.Opencart.utils.ElementUtil;
public class AccountPage extends BasePage{
	private WebDriver driver;
	private ElementUtil util;
	
	private By Fname=By.xpath("//input[@id='input-firstname']");
	private By Lname=By.xpath("//input[@id='input-lastname']");
	private By continuebtn=By.xpath("//input[@value='Continue' and @type='submit']");
	private By SuccessMsg=By.xpath("//div[@id='account-account']/child::div[contains(text(),'Success')]");
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		util=new ElementUtil(driver);
	}
	
	public void editfieldsLFnames(String FstName,String LstName) {
		util.waitForElementTobeLocated(Fname, 10);
		util.doSendKeys(Fname, FstName);
		util.waitForElementTobeLocated(Lname, 4);
		util.doSendKeys(Lname, LstName);
		
		System.out.println(FstName + " "+LstName);
	}
	
	public HomePage clickContinue() {
		util.doisDisplayed(continuebtn);
		util.doclick(continuebtn);
		return new HomePage(driver);
	}
	
	public String verifySuccessMsg() {
		util.waitForElementTobeLocated(SuccessMsg, 10);
		String successText=util.dogetText(SuccessMsg);
		System.out.println(successText);
		return successText;
		
		
	}

}
