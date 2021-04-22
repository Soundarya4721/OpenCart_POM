package com.qa.Opencart.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	WebDriver driver;

	/**
	 * 
	 * @param driver passing driver as an input and creating a constructor
	 */
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	}
	
	/**
	 * 
	 * @param value the value can be any xpath,id,linktext etc
	 * @return it can return any identifier such as xpath,id,linktext,tagname
	 */
	public By getLocator(String value) {
		return By.xpath(value);
	}
	
	/**
	 * 
	 * @param Locator can be path,id...
	 * @return it will return the list of elements
	 */
	public List<WebElement>getElements(By Locator){
		return driver.findElements(Locator);
	}
	
	/**
	 * *******************************Locator Utils ********************************************
	 */
	
	/**
	 * 
	 * @param Locator -Use a by locator and pass as a parameter to return web element.
	 * @return
	 */
	public WebElement getElement(By Locator) {
		return driver.findElement(Locator);
	}
	
	/**
	 * 
	 * @param Locator - passing a locator in getElement method to return webelement and do sendkeys
	 * @param value - passing the string value as input
	 */
	public void doSendKeys(By Locator,String value) {
		getElement(Locator).clear();
		getElement(Locator).sendKeys(value);
	}
	
	/**
	 * 
	 * @param Locator -  passing a locator in getElement method to return webelement and do click
	 */
	public void doclick(By Locator) {
		getElement(Locator).click();
	}
	
	/**
	 * 
	 * Actions Class is used to perform keyboard and mouse events with parameters as webelemnt,string
	 * @param Locator -string
	 * @param loca- by locator
	 */
	public void doActionSendKeys(String Locator,By loca) {
		Actions aaction=new Actions(driver);
		aaction.sendKeys(getElement(loca), Locator).perform();
	}
	
	/**
	 * 
	 * @param locator used to click.
	 */
	public void doActionclick(By locator) {
		Actions aaction=new Actions(driver);
		aaction.click(getElement(locator)).perform();
	}
	
	/**
	 * 
	 * The sendkeys method with move element is having parameters locators and value
	 *]
	 */
	public void doSendKeysWithMoveToElement(By locator,String value) {
		Actions actions=new Actions(driver);
		actions.moveToElement(getElement(locator)).sendKeys(value).build().perform();
	}
	
	/**
	 * Build and perform is used to compile and execute the action
	 * @param Locator by taking input with this parameter we will be clicking using the actionsclass
	 */
	public void doclickwithMoveToElement(By Locator) {
		Actions actions=new Actions(driver);
		actions.moveToElement(getElement(Locator)).click().perform();
	}
	
	/**
	 * 
	 * @param Locator Takes the web element as input 
	 * @return String
	 */
	public String dogetText(By Locator) {
		return getElement(Locator).getText();
	}
	
	/**
	 * 
	 * @param locator takes as an input 
	 * @return if the locator is displayed or not
	 */
	public boolean doisDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	/**
	 * 
	 * @param tagname takes tagname string as an input
	 * @return size if that tag
	 */
	public int getElementsCount(String tagname) {
		return driver.findElements(By.tagName(tagname)).size();
	}
	
	/**
	 * 
	 * @param tagname
	 * @param attributeName
	 * @return list of web element attributes
	 */
	public List<String> getAttributesList(String tagname,String attributeName){
		List<WebElement> elems=driver.findElements(By.tagName(tagname));
		List<String> attri=new ArrayList<String>();
		
		for(WebElement el:elems) {
			
			attri.add(el.getAttribute(attributeName));
		}
		return attri;
	}
	
	/**
	 * 
	 * @param Locator -clicks on the footer value with locator
	 * @param linktext
	 */
	public void doClickfromList(By Locator,String linktext) {
		List<WebElement> footerList=getElements(Locator);
		for(int i=0;i<footerList.size();i++) {
			String text=footerList.get(i).getText();
			if(text.equalsIgnoreCase(linktext)) {
				footerList.get(i).click();
				break;
			}
		}
	}
	
	/***
	 * ************************DropDown Utils********************************************
	 */
	/**
	 * 
	 * @param Locator -with the getElement method will be passing instead of driver.findelement(By.xpath("'))
	 * @param value will be the text which is visible on the screen
	 */
	public void doSelectDropDownByVisibleText(By Locator,String value) {
		Select select=new Select(getElement(Locator));
		select.selectByVisibleText(value);
	}
	
	public void doSelectDropDownByIndex(By Locator,int index) {
		Select select=new Select(getElement(Locator));
		select.selectByIndex(index);
	}
	
	public void doSelectDropDownByValue(By Locator,String text) {
		Select select=new Select(getElement(Locator));
		select.selectByValue(text);
	}
	
	public void doselectDropdownWithoutSelectClass(By locator,String value) {
		List<WebElement> optionslist=getElements(locator);
		for(WebElement e: optionslist) {
			String text=e.getText();
			if(text.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}
	}
	
	/**
	 * *********************************Wait Utils********************************************
	 * 
	 */
	public List<WebElement> visibilityOfAllElements(By locator,int timeout){
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getPageLinksText(By locator, int timeOut) {
		visibilityOfAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public int getPageLinksCount(By Locator,int timeout) {
		return visibilityOfAllElements(Locator, timeout).size();
	}
	
	public String waitForTitlePresent(String titleValue, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		 wait.until(ExpectedConditions.titleIs(titleValue));
		 return driver.getTitle();
	}
	
	
	public String waitForTitlePresent(String titleValue, int timeOut,int intervaltime) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut,intervaltime);
		 wait.until(ExpectedConditions.titleIs(titleValue));
		 return driver.getTitle();
	}
	
	/**
	 * 
	 * @param timeOut
	 * @return This will return the alert instance without switching to the alert
	 */
	public Alert waitForAlertToBePresent(int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public boolean waitFortheUrl(String Url,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(Url));
	}
	
	public WebElement waitForElementTobeLocated(By Locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}
	
	public WebElement waitForElementToBevisible(By Locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(Locator)));		
	}
	
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		 wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}
	
	public void waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		
	}
	
	/**
	 * This is custom dynamic wait to find the webelement
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement retryingElement(By locator) {
	
		WebElement element=null;
		int attempts=0;
		
		while(attempts<30) {
			try {
				element=driver.findElement(locator);
			}
			catch(StaleElementReferenceException e)
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}
			
			catch (NoSuchElementException e) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
				System.out.println("element is not found in attempt : " + (attempts + 1));
			}
			attempts++;
		}
		return element;
			
		}
	}
	
		

