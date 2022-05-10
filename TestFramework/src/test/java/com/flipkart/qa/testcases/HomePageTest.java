package com.flipkart.qa.testcases;

import static com.flipkart.qa.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.CartPage;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	WebDriverWait wait;
	HomePage home;
	LoginPage login;
	CartPage cartPage;

	public HomePageTest() {
		// TODO Auto-generated constructor stub
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		login = new LoginPage();
		home = login.loginAccount(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1,description = "Verifying the text of the home page")
	public void validateHomePageTitleTest(Method method) {
		startTest(method.getName(), "Verifying the text for the home page for Flipkart application");
		

		String title = home.validateHomePageTitle();
		Assert.assertEquals(title,
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}

//	@Test(priority=2)
//	public void verifyMobilePage() {
//		
//		wait = new WebDriverWait(driver,50);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='eFQ30H'][3]")));
//		String text=home.clickMobilesLink();
//		Assert.assertEquals(text,"Mobile Phones Bonanza Feb 2022");
//	}

	@Test(priority = 2,description = "Cart button click test")
	public void cartsPageLanding(Method method) {
		startTest(method.getName(), "Verifying whether the cart button is clickable or not at the home page");
		
		cartPage = home.clickCarts();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();

	}

}
