package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isShopNameDisplayed(String expectedText) {
		waitForElementVisible(driver, HomePageUI.SHOPNAME_LABEL);
		String actualText = getTextElement(driver, HomePageUI.SHOPNAME_LABEL);
		return actualText.equalsIgnoreCase(expectedText);
	}

}
