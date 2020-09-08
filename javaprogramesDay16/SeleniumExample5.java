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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SeleniumExample5 {
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
		driver.get("https://www.honda2wheelersindia.com/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='close']"))).click();
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("(//*[@class='owl-wrapper'])[2]/div/div/a").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Specifications")));
		driver.findElementByLinkText("Specifications").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='ENGINE']")));
		WebElement engine = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions action = new Actions(driver);
		action.moveToElement(engine).build().perform();
		String displacement = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//span[text()='Displacement']/following::span)[1]")))
				.getText();
		
		driver.findElementById("link_Scooter").click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='owl-wrapper'])[2]/div[2]/div/a")))
				.click();
		driver.findElementByLinkText("Specifications").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Engine']")));
		WebElement engine2 = driver.findElementByXPath("//a[text()='Engine']");
		Actions action2 = new Actions(driver);
		action.moveToElement(engine2).build().perform();
		String displacement2 = wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("(//span[text()='Displacement']/following::span)[1]")))
				.getText().trim();
		displacement2 = displacement2.replaceAll("[^\\d.]", "").trim();
		displacement = displacement.replaceAll("[^\\d.]", "").trim();
		System.out.println(displacement);
		System.out.println(displacement2);
		if (displacement.equals(displacement2)) {
			System.out.println("Bothe the engines dispalcement is Equal");
		} else {
			System.out.println("Bothe the engines dispalcement is NOT Equal");
		}
		driver.findElementByLinkText("FAQ").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Activa 125 BS-VI']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='li6']/a"))).click();

		Select select = new Select(driver.findElement(By.id("ModelID6")));

		WebElement option = select.getFirstSelectedOption();

		String defaultItem = option.getText();
		if (defaultItem.equals("Activa 125 BS-VI")) {
			System.out.println("Model selected is: "+defaultItem);
		}
		else {
			System.out.println("Model Selected is not Activa 125 BS-VI ");
		}
		
		driver.findElementById("submit6").click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='tblPriceMasterFilters']/tbody/tr/td[3]/a")));

		driver.findElementByXPath("//*[@id='tblPriceMasterFilters']/tbody/tr/td[3]/a").click();

		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(1));
		}
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("CityID")));
		Thread.sleep(3000);
		Select state = new Select(driver.findElement(By.id("StateID")));
		state.selectByVisibleText("Tamil Nadu");

		Select city = new Select(driver.findElement(By.id("CityID")));
		city.selectByValue("1524");

		driver.findElementByXPath("//button[text()='Search']").click();
		WebElement baseTable = driver.findElement(By.xpath("//table[@id='gvshow']/tbody"));
		List<WebElement> rows_table = baseTable.findElements(By.tagName("tr"));
		int rows_count = rows_table.size();
		System.out.println("VehicleModel" + "----" + "VehiclePrice");
		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			int columns_count = Columns_row.size();
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				
				if (!(celtext.equals("Chennai"))) {
					System.out.print(celtext + "----");
				}

			}
			System.out.println("-------------------------------------------------- ");
			
		}
		driver.quit();
	}
}
