package com.javaprogramesDay16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumExample6 {
	@Test
	public void TC001() throws Exception {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver850418387.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		driver.get("https://www.zalando.com/");
		
		Alert alert = driver.switchTo().alert();
		String alertMsg = alert.getText();
		System.out.println(alertMsg);
		alert.accept();
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uc-btn-accept-banner"))).click();
		WebElement element1 = driver.findElementByXPath("//a[text()='Free delivery and returns*']");
		Actions action = new Actions(driver);
		action.moveToElement(element1).build().perform();
		Thread.sleep(5000);
		
		//System.out.println(driver.getPageSource());
		
		WebElement clothing = driver.findElementByXPath("//span[text()='Clothing']");
		action.moveToElement(clothing).build().perform();
		driver.findElementByXPath("(//span[text()='Coats'])[1]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='filter by Material']"))).click();
		driver.findElementByXPath("//*[text()='Cotton']").click();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='filter by Length']"))).click();
		//driver.findElementByXPath("//span[text()='thigh-length']").click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='thigh-length']"))).click();		
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//figure[1]/parent::a").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@alt='blue'])[2]"))).click();
		driver.findElementByXPath("//button[@id='picker-trigger']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='XS']"))).click();
		String title = driver.getTitle();
		String[] title1 = title.split(" ");
		System.out.println(title1[0]);
		System.out.println(title1[1]);
		System.out.println(title1[2]);
		System.out.println(title1[4]);
		String url = driver.getCurrentUrl();
		String product = driver.findElement(By.xpath("(//h3)[1]")).getText();
		String productType= driver.findElementByXPath("(//h1)[1]").getText();
		System.out.println(product + productType);
		driver.findElementByXPath("//button[@aria-label='Add to bag']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Free delivery and returns*']"))).click();
		Thread.sleep(9000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='faq-dx-button']"))).click();
		driver.findElementByXPath("//button[@class='faq-dx-button']").click();
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(1));
		}
		driver.findElementById("prechat_customer_name_id").sendKeys("Rajesh");
		driver.findElementById("prechat_customer_email_id").sendKeys("Rajesh.raju2@gmail.com");
		driver.findElementByXPath("//*[text()='Start Chat']/parent::*").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("liveAgentChatTextArea"))).sendKeys("HI");
		driver.findElementByXPath("//*[text()='Send']").click();
		driver.close();
	}
}
