package ghtk.khachhang.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

public class Account_03_Login_PageObjectPattern{
	WebDriver driver;
	String email,password,loginPageUrl,shopName;
    LoginPageObject loginPage;
    HomePageObject homePage;
    Account account;
    
	//Pre-condition: thường dùng để set up những khởi tạo ban đầu như: khởi tạo chromedriver, set thời gian chờ load element, mở trình duyệt
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		// Khởi tạo ChromeDriver
        driver = new ChromeDriver();
        loginPage = new LoginPageObject(driver);
        homePage = new HomePageObject(driver);
        account = new Account();
        // Mở trình duyệt web
        System.out.println("PRE-CONDITION - STEP 1: Open Khachhang Application");
        driver.get("https://khachhang.giaohangtietkiem.vn/khach-hang/dang_nhap");
        
//        System.out.println("PRE-CONDITION - STEP 2: Get Login Page Url");
//        loginPageUrl = loginPage.getLoginPageUrl();
	}
	// Thực thi các testcase
    @Test
    //Kiểm tra login thành công
        public void TC_O2_CheckLoginSuccessful() {
        System.out.println("Login - STEP 2: Input email into textbox");
        email = account.getEmail();
        loginPage.inputToEmailTextbox(email);
        System.out.println("Login - STEP 3: Input pw into textbox");
        password = account.getPassword();
        loginPage.inputToPasswordTextbox(password);
        System.out.println("Login - STEP 4: Click button Đang Nhap");
        loginPage.clickToLoginButton();
        System.out.println("Login - STEP 5: Verify đăng nhập thành công");
        shopName = account.getShopName();
        Assert.assertTrue(homePage.isShopNameDisplayed(shopName));
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
