package com.flipkart.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.util.TestUtil;

public class ExcelDataFeedTest extends TestBase{
	
	public LoginPage login;
      public HomePage home;
      String sheetName="Sheet1";
      
	public ExcelDataFeedTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		login = new LoginPage();
	}
	
	@DataProvider
	public Object[][] getFLipkartLoginData(){
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority = 1,dataProvider="getFLipkartLoginData")
	public void loginTest(String username,String password) {
		home = login.loginAccount(username,password );
		System.out.println("HI");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	
	
}
