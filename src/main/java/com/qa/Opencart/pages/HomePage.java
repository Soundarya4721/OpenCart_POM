package com.qa.Opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.Opencart.base.BasePage;
import com.qa.Opencart.utils.Constants;
import com.qa.Opencart.utils.ElementUtil;

import io.qameta.allure.Step;
/**
 * 
 * @author ltatavarthy
 * having parent and child relationship i.e inheritence concept is applied with basepage
 * All the child pages have similar relationship with basepage.
 * This home page is an extension of loginpage
 */
public class HomePage extends BasePage{

	private WebDriver driver;
	private ElementUtil elemutil;
	
	private By myaccount_Title=By.xpath("//div//h2[text()='My Account']");
	private By editaccount=By.xpath("//div//h2[text()='My Account']/../ul/child::li/a[contains(text(),'Edit your')]");
	private By accounsection=By.xpath("//div/h2");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elemutil=new ElementUtil(driver);
	}
	
	@Step("Checking home page title..")
	public String getHomepageTitle() {
		return elemutil.waitForTitlePresent(Constants.Home_Title, 10);
	}
	
	public String getAccounttitle() {
		
		if(elemutil.doisDisplayed(myaccount_Title)) {
			return elemutil.dogetText(myaccount_Title);
		}
		return null;
		
		//Old code
		/**if(driver.findElement(myaccount_Title).isDisplayed()) {
			return driver.findElement(myaccount_Title).getText();
		}**/
		
	}
	
	public int getAccountSectionCount() {	
		return elemutil.getElements(accounsection).size();
		//return driver.findElements(accounsection).size();
	}
	
	public List<String> getAccountSectionList(){
		List<WebElement> accSectionList=elemutil.getElements(accounsection);
		List<String> accntList=new ArrayList<String>();
		
		for(WebElement elem:accSectionList) {
			accntList.add(elem.getText());
		}
		System.out.println(accntList);
		return accntList;
	}
	
	public AccountPage editaccount() {
		elemutil.doclick(editaccount);
		//driver.findElement(editaccount).click();
		return new AccountPage(driver);
	}
}
