package com.base;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
		
	public static ExtentReports createInstance() {
		String name = getReportName();
		String dir = System.getProperty("user.dir")+"/ExtentReports/";
		new File(dir).mkdir();
	
		String filePath = dir.toString() + name;
			
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	
	public static ExtentReports getInstance() {
		if(extent == null)
			createInstance();
		return extent;	
	}
	
	public static String getReportName() {
		Date d = new Date();
		String filename = "Automation_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return filename;		
	}

}
