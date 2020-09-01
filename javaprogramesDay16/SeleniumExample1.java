package com.javaprogramesDay16;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumExample1 {
	@Test
	public void TC001() throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,40);
		driver.manage().window().maximize();
		driver.get("https://www.bestbuy.com");
		driver.findElementByXPath("(//img[@alt='United States'])[1]").click();
		driver.findElementByXPath("//div[@class='c-modal-grid col-xs-6']/button[1]").click();
		driver.findElementByXPath("//button[text()='Products']/div").click();
		driver.findElementByXPath("//button[text()='Cell Phones']").click();
		
		System.out.println(driver.findElementByXPath("//button[@data-lid='ubr_mob_ip']").isDisplayed());
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@data-lid='ubr_mob_ip']"))).click();
				
		driver.findElementByLinkText("iPhone SE").click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//button[@data-rtb-selected-sku='6389069']"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@role='button'])[3]"))).click();
		Thread.sleep(2000);
		if(driver.findElementByXPath("//button[@id='SignInToastCloseButton']").isDisplayed()) {
			driver.findElementByXPath("//button[@id='SignInToastCloseButton']").click();
		}
		
		driver.findElementById("variaton-dropdown-Cell_Phones_Internal_Memory").click();
		driver.findElementByXPath("//a[@id='variations-dropdown-item-Cell_Phones_Internal_Memory-128GB']").click();
		
		
		driver.findElementByXPath("(//button[contains(text(),'Add to Cart')])[1]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[@class='carriers-page__carrier-choice'])[4]/button"))).click();
		String price1 = driver.findElementByXPath("((//div[@class='carriers-page__carrier-choice'])[4]/button)/span[2]/span").getText();
		System.out.println("Initial Price = "+ price1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//button[contains(@class,'carriers-page__continue')])"))).click();
		
		
		String totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='price-summary__total-value']"))).getText();
		System.out.println("Final TotalPrice = "+ totalPrice);
		if (price1.equals(totalPrice)) {
			System.out.println("Initial price and Final price both are EQUAL");
		}
		else {
			System.out.println("Initial price and Final price both are NOT EQUAL");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='checkout-buttons__checkout']/button"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='cia-form__controls ']/button"))).click();
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@id='fld-e-text']"))).getText());
		
		
		
		
		
	}

}