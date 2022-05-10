package com.flipkart.qa.testcases;

import static com.flipkart.qa.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
	}

//	@Test(priority = 1)
//	public void loginPageTitleTest() {
//		String title = loginPage.validateLoginPageTitle();
//		Assert.assertEquals(title,
//				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
//	}
//
//	@Test(priority = 2)
//	public void FlipartlogoTest() {
//		boolean flag = loginPage.validateFlipkartLogo();
//		Assert.assertTrue(flag);
//	}

	@Test(priority = 0,description = "Valid login scenario using correct username & password")
	public void loginTest(Method method) {
		startTest(method.getName(), "Login attempt using valid credentials");
		
		homePage = loginPage.loginAccount(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
