package ghtk.khachhang.packages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Packages_02_createPackagesByExcel {
	WebDriver driver;
	String root;
	
  @BeforeTest
  public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://khachhang.giaohangtietkiem.vn/khach-hang/dang_nhap");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		root = System.getProperty("user.dir");
  }
  

  @Test
  public void UploadFileSuccesful() throws InterruptedException {
	  driver.findElement(By.id("ShopEmail")).sendKeys("lienltb@ghtk.vn");
      driver.findElement(By.id("ShopPassword")).sendKeys("shoptét12345");
      driver.findElement(By.xpath("//span[contains(text(),'Đăng Nhập')]")).click();
      Assert.assertTrue(driver.findElement(By.xpath("//span[@id='code-name']")).isDisplayed());
      System.out.println("Login successful");
      Thread.sleep(200);

      driver.findElement(By.xpath("//a[@id='import-package-excel']")).click();
      System.out.println("Upload Excel file");
      Thread.sleep(200);
      driver.findElement(By.xpath("//*[@id='UploadOrderPickAddressId']")).click();
      List<WebElement> dropDownMenu = driver.findElements(By.xpath("//select[@id='UploadOrderPickAddressId']//option"));
      dropDownMenu.get(3).click();
      WebElement uploadFile = driver.findElement(By.id("UploadExcelFile"));
      String filePath = root + "\\files\\Giaohangtietkiem_mau_excel_don_hang.xlsx";
      uploadFile.sendKeys(filePath);
      driver.findElement(By.xpath("//*[@id='upload-excel-v2-form']/div[4]/button[2]")).click();
      Thread.sleep(500);
      driver.findElement(By.xpath("//button[contains(text(),'Xác nhận tải lên')]")).click();
      Thread.sleep(20000);
      String result = driver.findElement(By.xpath("//tbody/tr[1]/td[2]/span")).getText();
      Assert.assertEquals(result, "Đã hoàn thành");
      System.out.println("Create packages from Excel file successful");
    
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
}
