package com.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class SearchFeature extends TestBase{

	@FindBy(xpath = "//input[@data-page = '/search']")
	WebElement tbSearch;
	
	@FindBy(className = "search-container_btn-submit")
	WebElement btnSearch;
	
	@FindBy(xpath = "//li[contains(text(), 'Popular searches for the week')]")
	WebElement lblPopularSearches;
	
	@FindBy(xpath = "//div[@class = 'scp-only_list']//li[2]")
	WebElement lblPopularSearchFirstItem;
	
	@FindBy(xpath = "//li[@class = 'search-container_history_title'")
	WebElement lblHistoryTitle;
	
	@FindBy(xpath = "//li[@class = 'search-container_history_clear'")
	WebElement btnHistoryClear;
	
	Alert ab;
	
	public SearchFeature() {
		PageFactory.initElements(driver, this);
	}
	
	
	// Method to check if Search text-box is present on the Home page	
	public boolean searchBoxIsPresent() {
		return tbSearch.isDisplayed();
	}
	
	
	// Method to check if Search icon button is present on the Home page	
	public boolean searchButtonIsPresent() {
		return btnSearch.isDisplayed();
	}
	
	
	// Method to check the placeholder text of search text-box
	public String searchTextBoxPlaceholder() throws Exception {
		String text;
		
		if(searchBoxIsPresent() == true) {
			text = tbSearch.getAttribute("placeholder");
			return text;
		}else {
			throw new Exception();
			
			}
	}
	
	
	// Method to check if search container panel is displayed 
	public boolean popularSearchIsDisplayed() throws Throwable {
		if(searchBoxIsPresent() == true) {
			tbSearch.click();
			if(lblPopularSearches.isDisplayed())
				return true;
			else
				return false;
		}else {
			System.out.println("Error...Search text-box is not present!");
			throw new Exception();
		}
			
	}
	
	
	// Method to check if an alert box is displayed upon clicking search button
	public boolean alertIsDisplayed() throws Throwable {
		if(searchButtonIsPresent() == true && tbSearch.getText().isEmpty()) {
			btnSearch.click();
			ab = driver.switchTo().alert();
			ab.accept();
			return true;
		}else {
			System.out.println("Error...Search button is not present!");
			throw new Exception();
		}			
	}
	
	
	// Method to check the text in the alert box
	public String alertBoxText() {
		String alertText;
		
		if(searchButtonIsPresent() == true && tbSearch.getText().isEmpty()) {
			btnSearch.click();
			ab = driver.switchTo().alert();
			alertText = ab.getText();
			ab.accept();
			return alertText;
	    }else
	    	return "Search button is not present!";
	 }
	
	
	// Method to search a product
	public Products searchProduct(String prod) {
		if(searchBoxIsPresent() == true && searchButtonIsPresent() == true) {
			tbSearch.sendKeys(prod);
			btnSearch.click();
		}
		return new Products();
	}
	
	
	// Method to search first item in the search container panel
	public Products searchContainerPanelItem() {
		try {
			popularSearchIsDisplayed();
			lblPopularSearchFirstItem.click();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return new Products();
		
	}
}
