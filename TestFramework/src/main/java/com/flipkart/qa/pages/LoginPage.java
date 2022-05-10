package com.flipkart.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class LoginPage extends TestBase{
 
	@FindBy(xpath="//a[contains(text(),'Login')]")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(css="input[class='_2IX_2- VJZDxU']")
	WebElement username;
	
	
	@FindBy(css="input[class='_2IX_2- _3mctLh VJZDxU']")
	WebElement password;
	
	@FindBy(css="button[class='_2KpZ6l _2HKlqd _3AWRsL']")
	WebElement loginButton;
	
	@FindBy(css="img[class='_2xm1JU']")
	WebElement logo;
	
	//Initializing the page objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateFlipkartLogo() {
		return logo.isDisplayed();
	}
	
	//Method to enter text in the login section
	//Home Page is the landing page for the Login Page, hence we are returning it in this method
	public HomePage loginAccount(String un,String pswd)
	{
		username.sendKeys(un);
		password.sendKeys(pswd);
		
		loginButton.click();
		return new HomePage();
	}
	
	
	
	
	
	
	
}
