package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.utility.TestUtils;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/config/config.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/src/test/resources/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Select a browser");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}

}
