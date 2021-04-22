package com.qa.Opencart.testlisteners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.Opencart.base.BasePage;
import com.qa.Opencart.base.BaseTest;

import io.qameta.allure.Attachment;

public class TestAllureListener extends BasePage implements ITestListener{
	
	
	private static String getMethodName(ITestResult itestresult) {
		return itestresult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "page screenshot", type = "image/png")
	public byte[] saveScreenshot( WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Attachment(value = "{0}", type = "text/html")
	public static String attachment(String html) {
		return html;
	}
	
	 
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("am in onstart method" + getMethodName(result));
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("am in testsuccess method" + getMethodName(result));

	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("am in testfailure method" + getMethodName(result));
		Object testclass=result.getInstance();
		if(getDriver() instanceof WebDriver) {
			System.out.println("screenshot captured for the test method" +getMethodName(result));
			saveScreenshot(getDriver());
		}

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("am in testfailure method" + getMethodName(result));
		Object testclass=result.getInstance();
		if(getDriver() instanceof WebDriver) {
			System.out.println("screenshot captured for the test method" +getMethodName(result));
			saveScreenshot(getDriver());
		}
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("am in testfailure but with success percentage  method" + getMethodName(result));
		Object testclass=result.getInstance();
		if(getDriver() instanceof WebDriver) {
			System.out.println("screenshot captured for the test method" +getMethodName(result));
			saveScreenshot(getDriver());
		}

	}
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Am in onstart method"+ context.getName());		
	}
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Am in onfinish method"+ context.getName());		

	}

}
