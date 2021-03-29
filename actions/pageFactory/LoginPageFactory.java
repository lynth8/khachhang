package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class LoginPageFactory extends AbstractPage {

	WebDriver driver;
	
	@FindBy(how = How.ID, using = "ShopEmail")
	private WebElement emailTextbox;
	
	@FindBy(how = How.ID, using = "ShopPassword")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Đăng Nhập')]")
	private WebElement loginButton;
	
	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		emailTextbox.sendKeys(email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		passwordTextbox.sendKeys(password);
	}

	public void clickToLoginButton() {
		waitForElementVisible(driver, loginButton);
		loginButton.click();
	}
}
