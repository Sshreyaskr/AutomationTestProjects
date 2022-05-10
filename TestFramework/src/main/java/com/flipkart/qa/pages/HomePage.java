package com.flipkart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.util.TestUtil;

public class HomePage extends TestBase{
	
	public TestUtil util=new TestUtil();
	
	
	@FindBy(xpath="//*[@class='eFQ30H'][3]")
	WebElement mobilesSection;
	
	@FindBy(css="h1[class='_3vKRL2']")
	WebElement mobSectionText;
	
	@FindBy(css ="div[class='_1psGvi']")
	WebElement cartLogo;
	
	@FindBy(xpath = "//div[contains(text(),'Shubham')]")
	WebElement username;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	
	//Mobile Phones Bonanza Feb 2022
	public String clickMobilesLink() {
		mobilesSection.click();
		return mobSectionText.getText();
			
	}
	
	public CartPage clickCarts() {
		util.explicitWaitVisibility(30, "//span[contains(text(),'Cart')]");
		
		util.javaScriptExecutor2("//span[contains(text(),'Cart')]");
		return new CartPage();
	}
	
	
}
