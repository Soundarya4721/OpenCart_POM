package com.qa.Opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String Login_Title="Account Login";
	public static final String Home_Title="My Account";
	public static final int Account_sections=4;
	public static final String EditPage_Success_Msg="Success: Your account has been successfully updated.";
	public static final String Register_sheet="Registration";
	public static final String ACCOUNT_SUCCESS_MESSG = "Your Account Has Been Created";


	//constant list
	public static List<String> getAccountsList(){
		List<String> accountList=new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");		
		return accountList;
	}
}
