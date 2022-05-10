package com.flipkart.qa.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.qa.base.TestBase;

import io.netty.handler.codec.http.multipart.FileUpload;

public class TestUtil extends TestBase{

	
	public static int pageLoadTimeout=20;
	public static int implicitWait=25;
	public static WebDriverWait wait;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\shrey\\OneDrive\\Desktop\\Excel_Test_Data.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	//https://d.docs.live.net/9111d25e9453f1b7/Desktop/Excel_Test_Data.xlsx
	
	public void javaScriptExecutor(String locator) {
       WebElement domObject=driver.findElement(By.cssSelector(locator));
		
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", domObject);
	}
	
	public void javaScriptExecutor2(String locator) {
	       WebElement domObject=driver.findElement(By.xpath(locator));
			
			JavascriptExecutor executor=(JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", domObject);
		}
	
	public void explicitWaitVisibility(int seconds,String locator) {
		wait = new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void explicitWaitClickable(int seconds,String locator) {
		wait = new WebDriverWait(driver,seconds);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	}
	
	public String getTextFromElements(String locator) {
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(TestUtil.implicitWait, TimeUnit.SECONDS);
	}
	
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
//	public static void takeScreenshotAtEndOfTest() throws IOException {
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String currentDir = System.getProperty("user.dir");
//		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));	
//	}
	
	public static void takeScreenshotAtEndOfTest2() throws IOException{
		//The format how we want to see how our file name
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		//setting the path of the screenshot folder, within the current Project itself
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/failure_screenshots/" + formater.format(calendar.getTime()) + ".png"));
		
	}
	
	
}
