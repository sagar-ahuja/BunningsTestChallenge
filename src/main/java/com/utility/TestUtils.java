package com.utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.base.TestBase;

public class TestUtils extends TestBase{

	public static int PAGELOAD_TIMEOUT = 60;
	public static int IMPLICIT_WAIT = 60;

	// JavaScript Executor	
	public static void clickElement(WebDriver d, WebElement ele) {
		JavascriptExecutor js =  (JavascriptExecutor)d;
		js.executeScript("arguments[0].click()", ele);
	}

	
	// Screenshot function
	public static void takeScreenshotOnError() throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcFile, new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis()+".png"));
	}

}
