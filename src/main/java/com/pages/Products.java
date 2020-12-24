package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class Products extends TestBase{
	
	@FindBy(xpath = "//h1[@class = 'responsive-search-title']")
	WebElement title;
	
	public Products() {
		PageFactory.initElements(driver, this);
	}

	
	// Method to verify the title of Product search
	public boolean searchTitle() {
		if(title.isDisplayed() == true) {
			if(title.getText().contains(prop.getProperty("product")))
				return true;
			else
				return false;
		}else
			return false;
	}
}
