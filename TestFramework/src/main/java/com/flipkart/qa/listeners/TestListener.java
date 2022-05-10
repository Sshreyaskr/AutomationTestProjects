package com.flipkart.qa.listeners;

import static com.flipkart.qa.extentreports.ExtentTestManager.getTest;

import com.aventstack.extentreports.Status;
import java.util.Objects;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.extentreports.ExtentManager;
import com.flipkart.qa.extentreports.ExtentTestManager;
import com.flipkart.qa.logs.Log;

public class TestListener extends TestBase implements ITestListener {
	
	private ITestListener list;
	private ExtentTestManager test;
	
	public TestListener() {
		this.test=new ExtentTestManager();
	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		Log.info("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", this.driver);
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		Log.info("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for ExtentReports reporting!
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is starting.");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is succeed.");
		// ExtentReports log operation for passed tests.
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is failed.");
		Throwable t = null;

		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver driver2 = TestBase.driver;

		// Take base64Screenshot screenshot for extent reports
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(driver2)).getScreenshotAs(OutputType.BASE64);

		// ExtentReports log and screenshot operations for failed tests.
		getTest().log(Status.FAIL, "Test Failed",
				getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		Log.info(getTestMethodName(iTestResult) + " test is skipped.");
		// ExtentReports log operation for skipped tests.
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}
