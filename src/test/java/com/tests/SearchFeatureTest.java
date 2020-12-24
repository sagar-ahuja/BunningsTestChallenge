package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.Products;
import com.pages.SearchFeature;

public class SearchFeatureTest extends TestBase{
	
	SearchFeature search;
	Products products;

	public SearchFeatureTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		search = new SearchFeature();
	}
	
	@Test 
	public void verifySearchBoxPlaceholderText() {
		String ptext = "";
		try {
			ptext = search.searchTextBoxPlaceholder();
			Assert.assertEquals(ptext, prop.getProperty("placeholderText"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void verifyProductSearch() {
		products = search.searchProduct(prop.getProperty("product"));
		Assert.assertTrue(products.searchTitle(), "Title does not contain the searched keyword: "+prop.getProperty("product"));
	}
	
	@Test 
	public void verifyAlertBox() {
		try {
			Assert.assertTrue(search.alertIsDisplayed(), "Alert box is not displayed");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyAlertBoxText() {
		String atext = search.alertBoxText();
		Assert.assertEquals(atext, "Please enter search keywords");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
