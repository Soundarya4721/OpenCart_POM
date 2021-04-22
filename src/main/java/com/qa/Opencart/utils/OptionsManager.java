package com.qa.Opencart.utils;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions cop;
	private FirefoxOptions fop;
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	
	public  ChromeOptions getChromeOptions() {
		cop=new ChromeOptions();
		if(prop.getProperty("headless").trim().equals("true"))cop.addArguments("--headless");
		if(prop.getProperty("incognito").trim().equals("true"))cop.addArguments("--incognito");
		return cop;
	}
}
