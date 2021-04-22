package com.qa.Opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver){
		this.driver=driver;
	}
	
	public void flash(WebElement element) {
		JavascriptExecutor js=((JavascriptExecutor)driver);
		String bgcolor=element.getCssValue("backgroundColor");
		for(int i=0;i<20;i++) {
			
		}
	}
}
