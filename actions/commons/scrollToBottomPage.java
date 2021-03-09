package commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class scrollToBottomPage {
    public Object work(WebDriver driver) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
}
