package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	/*Web Browser*/
	
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
        //Set thời gian chờ load element. Nếu chờ quá 10s mà không tìm thấy element nó sẽ timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}
	
	/*Web Element*/
	
	public void clickToElement(WebDriver driver, String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.id(locator));
		element.sendKeys(value);
	}
	
	public void selectItemInHtmlDropdown(WebDriver driver, String locator, String value) {
		select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInHtmlDropdown(WebDriver driver, String locator) {
		select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}
	
    public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String allItemXpath,
	            String expectedValueItem) throws Exception {
		element = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].click;", element);
		Thread.sleep(1000);
		
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		elements = driver.findElements(By.xpath(allItemXpath));
		for (WebElement childElement : elements) {
			if (childElement.getText().contains(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntroView(true);", childElement);
					Thread.sleep(1000);
					javascriptExecutor.executeScript("arguments[0].click();", childElement);
				}
				Thread.sleep(1000);
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attName) {
		return driver.findElement(By.xpath(locator)).getAttribute(attName);
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public int countElementNumber(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}
	
	public void checkTheCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if(!element.isSelected())	element.click();
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if(element.isSelected())	element.click();
	}
	
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	public boolean isControlSelected(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isEnabled();
	}
	
	public void switchToChildWindownById(WebDriver driver, String parentId){
        Set<String> allWindowns = driver.getWindowHandles();
        for(String runWindown : allWindowns){
            if(!runWindown.equals(parentId)){
                driver.switchTo().window(runWindown);
                break;
            }
        }
    }

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindowns = driver.getWindowHandles();
        for(String runWindown : allWindowns){
            driver.switchTo().window(runWindown);
            String currentWin = driver.getTitle();
            if(currentWin.equals(title)){
                break;
            }
        }
    }
	
	public void closeToWindownWithoutParent(WebDriver driver, String parentID){
        Set<String> allWindowns = driver.getWindowHandles();
        for(String runWindown : allWindowns){
            if(!runWindown.equals(parentID)){
                driver.switchTo().window(runWindown);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

	public void hoverMuseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(locator))).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath(locator))).perform();
    }

    public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(driver.findElement(By.xpath(locator)), key).perform();
    }
    
    public Object executeForBrowser(WebDriver driver, String javaSript) {
        javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript(javaSript);
    }

    public boolean verifyTextInInnerText(WebDriver driver, String locator, String textExpected) {
        javascriptExecutor = (JavascriptExecutor) driver;
        String textActual = (String) javascriptExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        System.out.println("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }
    
    public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) javascriptExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
        if (status)	return true; 
        else	return false;
    }
    
    public void clickToElementByJS(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }
    
    public void scrollToElement(WebDriver driver, String locator) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        element = driver.findElement(By.id(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void scrollToBottomPage(WebDriver driver) {
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    
    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        element = driver.findElement(By.xpath(locator));
        javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }
    
    public void waitForElementPresence(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, 30);
        By byLocator = By.xpath(locator);
        wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, 5);
        By byLocator = By.id(locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, 30);
        By byLocator = By.xpath(locator);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator));
    }
    
    public void waitForElementInvisible(WebDriver driver, String locator) {
        wait = new WebDriverWait(driver, 30);
        By byLocator = By.xpath(locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
    }
    
	private Select select;
	private WebElement element;
	private List<WebElement> elements;
	private JavascriptExecutor javascriptExecutor;
	private WebDriverWait wait;
	private Actions action;
}
