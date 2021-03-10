package ghtk.khachhang.account;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class Account_01_Login {
	WebDriver driver;
    public static final String url_statistic = "https://khachhang.giaohangtietkiem.vn/khachhang";
    AbstractPage abstractPage;
    
	//Pre-condition: thường dùng để set up những khởi tạo ban đầu như: khởi tạo chromedriver, set thời gian chờ load element, mở trình duyệt
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		// Khởi tạo ChromeDriver
        driver = new ChromeDriver();
        abstractPage = new AbstractPage();
        // Mở trình duyệt web
        System.out.println("Login - STEP 1: Open Url");
        driver.get("https://khachhang.giaohangtietkiem.vn/khach-hang/dang_nhap");
        //Abstract Page
        abstractPage.openURL(driver, "https://khachhang.giaohangtietkiem.vn/khach-hang/dang_nhap");
        // Mở to full cửa sổ trình duyệt
        driver.manage().window().maximize();
        // chỉ là lệnh in ra dòng chữ "Get link url"
        System.out.println(abstractPage.getCurrentURL(driver)); 
	}
	// Thực thi các testcase
    @Test
    // Kiểm tra title của trang
    public void TC_O1_Title(){
        //phương thức getTile là để lấy ra tile của trang web
        String actualTitle = driver.getTitle();
        //Khai báo biến dạng string là giá trị mong đợi
        String expectedTitle = "GHTK - Dịch vụ giao hàng trong ngày chuyên nghiệp";
        // So sánh kết quả thực tế tế với kết quả mong đợi. Nếu bằng nhau thì passed. Không giống nhau là TC failed
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @Test
    //Kiểm tra login thành công
        public void TC_O2_CheckLoginSuccessful() {
        //1. Mở trình duyệt (đã thực hiện ở hàm beforeClass
        //2. Điền email của shop vào ô textbox
        System.out.println("Login - STEP 2: Input emailId into textbox");
        abstractPage.sendkeyToElement(driver.findElement(By.id("ShopEmail")), "lienltb@ghtk.vn");
        //driver.findElement(By.id("ShopEmail")).sendKeys("lienltb@ghtk.vn");
        //3. Điền password
        System.out.println("Login - STEP 3: Input pw into textbox");
        abstractPage.sendkeyToElement(driver.findElement(By.id("ShopPassword")), "shoptét12345");
        //driver.findElement(By.id("ShopPassword")).sendKeys("shoptét12345");
        //4. Click button Đăng Nhập
        System.out.println("Login - STEP 4: Click button Đang Nhap");
        driver.findElement(By.xpath("//span[contains(text(),'Đăng Nhập')]")).click();
        //5. Verify đăng nhập thành công. (Đăng nhập thành công sẽ hiển thị dòng text vd: "S14791714 - Shop test lien"
        System.out.println("Login - STEP 5: verify đăng nhập thành công");
        String actualLogin = driver.findElement(By.xpath("//span[@id='code-name']")).getText();
        //6. So sánh actual với expected
        Assert.assertEquals(actualLogin,"S14791714 - Shop test lien");
    }
    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
