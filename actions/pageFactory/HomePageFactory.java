package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class HomePageFactory extends AbstractPage{
	WebDriver driver;
	
	@FindBy(how = How.ID, using = "code-name")
	private WebElement shopName;
	
	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isShopNameDisplayed(String expectedText) {
		waitForElementVisible(driver, shopName);
		String actualText = shopName.getText();
		return actualText.equalsIgnoreCase(expectedText);
	}
}
