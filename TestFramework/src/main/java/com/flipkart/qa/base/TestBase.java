package com.flipkart.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.flipkart.qa.util.TestUtil;
import com.flipkart.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventlistener;
	
	public TestBase() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/flipkart/qa/config/config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver initialization() {
		String browser=prop.getProperty("browser");
		
		//C:\Users\shrey\Dropbox\My PC (LAPTOP-L25OGKCD)\Downloads\chromedriver_win32 _Version_100.0.4896.60
		
		if(browser.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\shrey\\Dropbox\\My PC (LAPTOP-L25OGKCD)\\Downloads\\chromedriver_win32 _Version_100.0.4896.60\\chromedriver.exe");	
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shrey\\Dropbox\\My PC (LAPTOP-L25OGKCD)\\Downloads\\chromedriver_win32\\chromedriver.exe");	
			driver = new ChromeDriver();
		}
		
		e_driver=new EventFiringWebDriver(driver);
		eventlistener=new WebEventListener();
		e_driver.register(eventlistener);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicitWait, TimeUnit.SECONDS);
		
		
		driver.get(prop.getProperty("url"));
		return driver;
	
	}

}
