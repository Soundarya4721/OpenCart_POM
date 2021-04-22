package com.qa.Opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.Opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	/**
	 * These variables are used in class level hence declaring on a global context..
	 */
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tldriver=new ThreadLocal<WebDriver>();
	
	/**
	 * This method initializes the driver based on the given browser name as input...
	 * @param browser
	 * @return This returns web-driver driver
	 */
	public WebDriver init_driver(String browserType) {
		System.out.println("The browser is : "+browserType);
		optionsmanager=new OptionsManager(prop);
		if(browserType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remotedriver("chrome");
			}
			else {
				tldriver.set(new ChromeDriver());
			}
			//driver=new ChromeDriver();
		}
		
		if(browserType.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		}
		
		if(browserType.equalsIgnoreCase("Safari")) {
			//driver=new SafariDriver();
			tldriver.set(new SafariDriver());
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	
	/**
	 * get driver using thread local
	 */
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	/**
	 * This is to load the properties file and initialize its properties
	 * FIS establish a connection between properties and the class
	 * loads the properties to the properties object
	 * properties is part of java collections..its actually from collections utility 
	 * Returns properties for pop reference.
	 */
	public Properties init_properties() {
		prop=new Properties();
		try {
			FileInputStream fis=new FileInputStream("/Users/ltatavarthy/EclipsPractice/Opencart_Pom/src/main/java/com/qa/Opencart/config/config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	
	/**
	 * 
	 * @return the url when web driver is opened.
	 */
	public WebDriver getUrl() {
		String url=prop.getProperty("url");
		 getDriver().get(url);
		 return getDriver();	 
	}
	
	/**
	 * This is used to take the screenshot and return the path of screenshot
	 */
	public String getScreenshot() {
		File src=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshots"+System.currentTimeMillis()+".png";
		File destination =new File(path);
		try {
			FileUtils.copyFile(src, destination);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	public void init_remotedriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap=DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY,optionsmanager.getChromeOptions());
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
