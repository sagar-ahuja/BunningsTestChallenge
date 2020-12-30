package com.base;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.utility.TestUtils;

public class TestListeners implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(context.getClass().getSimpleName());
		parentTest.set(parent);
	}	
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest child = parentTest.get().createNode(result.getMethod().getMethodName());
		test.set(child);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<b> Test method - "+ result.getMethod().getMethodName() + " - Passed!";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.get().log(Status.PASS, m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail("<details><summary><b><font color = red> Exception occurred: Click to see details "+
							"</font></b></summary>"+ exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		
		String path = TestUtils.takeScreenshotOnError(methodName);
		try {
			 test.get().fail("<b><font color = red> Screenshot: </font></b>", 
					 MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(IOException e) {
			test.get().fail("Test failed...screenshot could not be attached!");
		}
		
		String logText = "<b> Test method - "+ methodName + " - Failed!";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b> Test method - "+ result.getMethod().getMethodName() + " - Skipped!";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.get().log(Status.SKIP, m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		if(extent != null)
			extent.flush();
	}

}
