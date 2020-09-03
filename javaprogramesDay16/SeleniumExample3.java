package com.javaprogramesDay16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumExample3 {
	@Test
	public void TC001() throws Exception {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver850418387.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElementById("twotabsearchtextbox").sendKeys("outslayer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='suggestions']/div[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='a-price-whole'])[1]")));
		List<WebElement> pricelist = driver.findElementsByXPath("//span[@class='a-price-whole']");
		ArrayList<Integer> price = new ArrayList<Integer>();
		for (WebElement webElement : pricelist) {
			price.add(Integer.valueOf(webElement.getText().replace(",", "")));
		}
		System.out.println("Price list after Sorting");
		pricelist.get(price.indexOf(Collections.max(price))).click();
		/*
		 * System.out.println(price.indexOf(Collections.max(price)));
		 * System.out.println(price.indexOf(Collections.min(price)));
		 */
		Collections.sort(price);
		for (Integer integer : price) {
			System.out.print(integer + ", ");
		}
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(1));
		}
		String selecctedProdName = driver.findElementByXPath("//span[@id='productTitle']").getText().trim();
		String selectprodCost = driver.findElementById("priceblock_saleprice").getText();
		System.out
				.println("\nSelected product is: " + selecctedProdName + "\n And product Price is =" + selectprodCost);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlb-ptc-btn-native"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue"))).click();
		String errorMsg = "";
		try {
			errorMsg = driver.findElementByXPath("//div[@id='auth-email-missing-alert']/div/div").getText();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			errorMsg = driver.findElementByXPath("(//div[@class='a-alert-content'])[1]/ul/li/span").getText();
		}
		if (!errorMsg.isEmpty()) {
			System.out.println("\nError messgae: " + errorMsg);
		}
		driver.quit();
	}
}