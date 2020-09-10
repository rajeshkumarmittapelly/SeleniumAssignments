package com.javaprogramesDay16;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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

public class SeleniumExample7 {
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
		driver.get("https://www.nykaa.com/");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@class='menu-dropdown-icon']//a)[1]")));
		WebElement brands = driver.findElementByXPath("(//li[@class='menu-dropdown-icon']//a)[1]");
		Actions action = new Actions(driver);
		action.moveToElement(brands).build().perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[@class='BrandsCategoryHeading']/a)[1]")));
		WebElement popular = driver.findElementByXPath("(//div[@class='BrandsCategoryHeading']/a)[1]");
		action.moveToElement(popular).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//img[@src='https://adn-static2.nykaa.com/media/wysiwyg/2019/lorealparis.png']"))).click();
		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(1));
		}
		Boolean title = driver.getTitle().contains("L'Oreal Paris");
		assertTrue(title);
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//*[@title='POPULARITY']/following::span)[1]"))).click();
		driver.findElementByXPath("//*[text()='customer top rated']/following-sibling::div").click();
		// driver.findElementByXPath("//div[text()='Category']/following-sibling::div").click();
		Thread.sleep(5000);
		System.out.println(driver.findElementByXPath("//div[text()='Category']/following-sibling::div").isDisplayed());
		driver.findElement(By.xpath("//div[text()='Category']/following-sibling::div")).click();
		driver.findElementByXPath("(//span[contains(text(),'Shampoo')])[1]/following-sibling::div").click();
		String appliedFilter = driver.findElementByXPath("//*[@class='pull-left applied-filter-lists']/li").getText();

		assertEquals(appliedFilter.replace("close", ""),"Shampoo");
		if (appliedFilter.replace("close", "").equals("Shampoo")) {
			System.out.println("Shampoo filter is selected");
		} else {
			System.out.println("Shampoo filter is not selected");
		}
		List<WebElement> listShampoo = driver
				.findElements(By.xpath("//div[@class='m-content__product-list__title']/h2/span"));
		for (int i = 0; i < listShampoo.size(); i++) {
			if (listShampoo.get(i).getText().equals("L'Oreal Paris Colour Protect Shampoo")) {
				driver.findElementByXPath(
						"(//div[@class='tags-offer-container']/following-sibling::a)[" + (i + 1) + "]").click();
			}
		}
		ArrayList<String> availableWindows2 = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows2.get(2));
		}
		driver.findElementByXPath("//span[text()='175ml']").click();
		String price = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		driver.findElementByXPath("(//*[text()='ADD TO BAG'])[1]").click();
		Thread.sleep(4000);
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		String grandTotal = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[text()='Grand Total']/following-sibling::div")))
				.getText();
		System.out.println("Total Price:" + grandTotal.replaceAll("\\W", ""));
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By
				.xpath("//div[@class='second-col']/button")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By
				.xpath("//button[text()='CONTINUE AS GUEST']")))
				.click();
		String errorMessage = driver.findElementByXPath("//div[contains(@class,'generic-msg-bar')]/div").getText();
		System.out.println("Error Message:\n" + errorMessage);
	}
}
