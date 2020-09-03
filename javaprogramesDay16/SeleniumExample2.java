package com.javaprogramesDay16;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumExample2 {
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
		driver.get("https://login.salesforce.com/");
		driver.findElementById("username").sendKeys("samdavid@testleaf.com");
		driver.findElementById("password").sendKeys("samchennai92");
		driver.findElementById("Login").click();
		driver.findElementByXPath("(//span[text()='Create'])/parent::a").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-id='userCreateMenuItem']")))
				.click();

		WebElement frame = driver.findElementByXPath("//iframe[contains(@title,'New User')]");
		driver.switchTo().frame(frame);
		System.out.println(driver.findElementById("name_firstName").isDisplayed());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name_firstName"))).click();
		driver.findElementById("name_firstName").sendKeys("RajeshKumar2");
		driver.findElementById("name_lastName").sendKeys("Mittaplelly2");
		driver.findElementById("Alias").click();
		driver.findElementById("Alias").clear();
		driver.findElementById("Alias").sendKeys("Rajesh");
		driver.findElementById("Email").sendKeys("rajesh.raju225@gmail.com");
		driver.findElementById("Username").click();
		driver.findElementById("Username").clear();
		driver.findElementById("Username").sendKeys("RajeshMittapelly3@gmail.com");
		driver.findElementById("CommunityNickname").click();
		driver.findElementById("CommunityNickname").clear();
		driver.findElementById("CommunityNickname").sendKeys("Raju3");
		WebElement roleElement = driver.findElementById("role");
		Select role = new Select(roleElement);
		role.selectByVisibleText("Customer Support, International");

		WebElement userLiceneseElement = driver.findElementById("user_license_id");
		Select userLicenese = new Select(userLiceneseElement);
		userLicenese.selectByVisibleText("Identity");
		WebElement profileElement = driver.findElementById("Profile");
		Select profile = new Select(profileElement);
		profile.selectByVisibleText("Identity User");

		driver.findElementByXPath("(//*[@name='save'])[2]").click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@title,'User')]")));
		System.out.println(driver.findElementByXPath("//iframe[contains(@title,'User')]").isDisplayed());
		WebElement frame2 = driver.findElementByXPath("//iframe[contains(@title,'User')]");
		driver.switchTo().frame(frame2);
		String createdEmail = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Email']/following::td[1]/a")))
				.getText();
		System.out.println(createdEmail);
		driver.switchTo().defaultContent();

		driver.findElementByClassName("uiImage").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log Out"))).click();

	}

}