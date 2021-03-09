package ghtk.khachhang.packages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.scrollToBottomPage;

public class Packages_01_createPackage {
	WebDriver driver;
    public static final String url_statistic = "https://khachhang.giaohangtietkiem.vn/khachhang";
    
	//Pre-condition: thường dùng để set up những khởi tạo ban đầu như: khởi tạo chromedriver, set thời gian chờ load element, mở trình duyệt
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		// Khởi tạo ChromeDriver
        driver = new ChromeDriver();
        // Mở trình duyệt web
        System.out.println("Login - STEP 1: Open Url");
        driver.get("https://khachhang.giaohangtietkiem.vn/khach-hang/dang_nhap");
        //Set thời gian chờ load element. Nếu chờ quá 10s mà không tìm thấy element nó sẽ timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Mở to full cửa sổ trình duyệt
        driver.manage().window().maximize();
        // chỉ là lệnh in ra dòng chữ "Get link url"
        System.out.println("Get link url");
	}
	// Thực thi các testcase
	@Test
//  Đăng đơn hàng thành công
  	public void TC_O1_CreatePackage() throws InterruptedException {
  	//1. Mở form đăng đơn hàng
      System.out.println("CreatePackages - STEP 6: Open form");
      driver.findElement(By.xpath("//a[@id='check-overload']")).click();
      //2. Verify mở form thành công
      System.out.println("CreatePackages - STEP 7: Verify mở form thành công");
      String actualTitleForm = driver.findElement(By.xpath("//h3[contains(text(),'Nhập thông tin đơn hàng')]")).getText();
      //3. So sánh actual với expected
      Assert.assertEquals(actualTitleForm,"Nhập thông tin đơn hàng");
      //4. Input info
      System.out.println("CreatePackages - STEP 8: Input form");
      //sđt
      driver.findElement(By.id("Package0CustomerTelMask")).sendKeys("0929190061");
      // name
      driver.findElement(By.id("Package0CustomerFullname")).sendKeys("QA Test không giao lấy");
      //địa chỉ
      driver.findElement(By.id("Package0CustomerFirstAddress")).sendKeys("Công ty CP GHTK");
      driver.findElement(By.xpath("//span[@id='select2-Package0CustomerSpecificId-container']")).click();
      driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("Tòa VTV");
//      Select shipAddress = new Select(driver.findElement(By.xpath("//*[@id=\"Package0CustomerSpecificId\"]")));-->bỏ do câu này sẽ không xác định dk phần tử trong list element đã kích
//      shipAddress.selectByValue("Tòa VTV Số 8 Phạm Hùng"); -->bỏ
      // thay bằng
      Thread.sleep(1000);
        List<WebElement> dropDownMenu = driver.findElements(By.xpath("//ul[@class='select2-results__options']//li"));
      dropDownMenu.get(0).click();
      //hình thức gửi hàng
      driver.findElement(By.xpath("//input[@id='Package0PickOptionCod']")).click();
      //tên sản phẩm
      driver.findElement(By.name("data[Package][0][Order][0][product_name]")).sendKeys("Test đăng đơn hàng");
      //khối lượng
      driver.findElement(By.name("data[Package][0][Order][0][weight]")).sendKeys("1.04");
      //tiền thu hộ
      driver.findElement(By.name("data[Package][0][pick_money]")).sendKeys("100000");
      //5. Submit packages
      scrollToBottomPage stbp = new scrollToBottomPage();
      stbp.work(driver);
      System.out.println("CreatePackages - STEP 8: Submit");
      driver.findElement(By.xpath("//*[@id='submit-packages']")).click();

      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      Assert.assertEquals(driver.getCurrentUrl(),url_statistic);
      
  }
	@AfterClass
    public void afterClass() {
        //driver.quit();
    }
}
