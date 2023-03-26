package com.webauto.suite;

import java.io.IOException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webauto.framework.Configuration;
import com.webauto.framework.Driver;

public class SuiteInitialization {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	public void startReport() {
		System.out.println("BEFORE SUITE START");

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReports/SmokeTest_AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", Configuration.get("url"));

		htmlReporter.config().setDocumentTitle("Regression");
		htmlReporter.config().setReportName(Configuration.get("ReportName"));
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
		 Driver.current().quit();
	}

	// @BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		// System.out.println("in Before Class");
		DesiredCapabilities cap = new DesiredCapabilities();
		Driver.add(Configuration.get("browser"), cap);
		Driver.current().get(Configuration.get("url"));
		Driver.current().manage().window().maximize();
	}

	// @AfterClass(alwaysRun = true)
	public void driverQuit() throws Exception {
		// System.out.println("in After Class");
		Driver.current().quit();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUpMethod() throws Exception {
		// System.out.println("in Before Method");
		/*
		 * protected void startSession(Method method) throws Exception {
		 * 
		 * String testName = method.getName();
		 */
		setUp();
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			// logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()
			// + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));

		}
		System.out.println("in After Method");
		// Driver.current().quit();
		
		 tearDown();
	}

}
