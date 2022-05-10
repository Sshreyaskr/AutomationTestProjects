package com.flipkart.qa.testcases;

import static com.flipkart.qa.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class CartPageTest extends TestBase{
	
	LoginPage login;
	CartPage cart;
	TestUtil util;
	HomePage home;
	
	CartPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		login=new LoginPage();
		util=new TestUtil();
		cart=new CartPage();
		
		/*Before testing the methods for the cart page we have to login into the account &
		    click the cart button first*/
		home=login.loginAccount(prop.getProperty("username"), prop.getProperty("password"));
	
		util.explicitWaitVisibility(30, "//span[contains(text(),'Cart')]");
	    util.javaScriptExecutor2("//span[contains(text(),'Cart')]");
	}
	
	@Test(priority=0,description = "Verify the number of items in my cart")
	public void verifycartItemslist(Method method) {
		startTest(method.getName(), "Incorrect number of cart items for the profile.");
		
		String cartText=cart.cartItemslist();
    	Assert.assertEquals(cartText,"My Cart (3)");
	}
	
//	@Test(priority=1)
//	public void verifycartTotalSum() {
////		WebElement cartLogo=driver.findElement(By.xpath("//span[contains(text(),'Cart')]"));
////		cartLogo.click();
//		System.out.println("Hello");
//	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
		

}
