package com.flipkart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class CartPage extends TestBase{
	
	TestUtil util=new TestUtil();
	
	@FindBy(xpath = "//div[contains(text(),'My Cart (3)')]")
	WebElement cartItems;
	
	@FindBy(xpath=" //span[contains(text(),'₹39,990')]")
	WebElement cartTotal;
	
	
	public String cartItemslist() {
		util.explicitWaitVisibility(30, "//div[contains(text(),'My Cart (3)')]");
		
		//String text=driver.findElement(By.xpath("//div[contains(text(),'My Cart (1)')]")).getText();
		
		String text=util.getTextFromElements("//div[contains(text(),'My Cart (3)')]");
		return text;
	}
	
	public String cartTotalSum() {
		return cartTotal.getText();
	}

}
