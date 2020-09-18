package com.javaprogramesDay16;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumExample10 {
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
		driver.get("https://www.myntra.com/");
		WebElement women = driver.findElementByXPath("(//a[text()='Women'])[1]");
		 Actions actions = new Actions(driver);
		 actions.moveToElement(women).perform();
		 wait.until( ExpectedConditions .visibilityOfElementLocated(By
				 .xpath("(//a[text()='Jackets & Coats'])"))).click();		
		String totalItems = driver.findElementByXPath("//span[@class='title-count']").getText();
		 int count = Integer.parseInt(totalItems.replaceAll("\\D",""));
		 System.out.println(count);
		 int sumByCate=0;
		 List<WebElement> categories = driver.findElementsByXPath("//span[@class='categories-num']");
		 for (WebElement element : categories) {
			 sumByCate= sumByCate+ Integer.parseInt((element.getText().replaceAll("\\D","")));
		}
		 System.out.println("Total item By Category"+sumByCate);
		 driver.findElementByXPath("//ul[@class='categories-list']/li/label[text()='Coats']/div").click();
		 driver.findElementByXPath("//div[@class='brand-more']").click();
		 wait.until( ExpectedConditions .visibilityOfElementLocated(By
				 .xpath("//input[@placeholder='Search brand']"))).sendKeys("Mango");
		 Thread.sleep(3000);
		 wait.until( ExpectedConditions .visibilityOfElementLocated(By
				 .xpath("//label[text()='MANGO']"))).click();
		 driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		 Thread.sleep(5000);
		 List<WebElement> brands = driver.findElementsByXPath("//div[@class='product-productMetaInfo']/h3");
		 for (WebElement element : brands) {
			 Assert.assertEquals(element.getText(), "MANGO");			 
		}
		 List<WebElement> listofItems = driver.findElementsByXPath("(//div[@class='product-productMetaInfo'])/parent::a");
		 HashMap<WebElement, Integer> map = new HashMap<WebElement, Integer>();
		 List<Integer> percentages =new ArrayList<Integer>();
		 for (int i = 0; i < listofItems.size(); i++) {			 
			int percentage ;
					try {
						percentage = Integer.parseInt(driver.findElementByXPath("(//div[@class='product-productMetaInfo'])["+ (i+1) +"]/div/span[2]").getText().replaceAll("\\D",""));
					} 
					catch (NoSuchElementException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						percentage = 0;
					}
					percentages.add(percentage);
					map.put(listofItems.get(i), percentage);
		}
		 Collections.sort(percentages);
		 for (Integer percent : percentages) {
			System.out.print(percent+",");
		}
		 int maxValueInMap=(Collections.max(map.values()));
		 System.out.println(maxValueInMap);
		 int position ;
		 for (Entry<WebElement, Integer> entry : map.entrySet()) {  // Itrate through hashmap
	            if (entry.getValue()==maxValueInMap) {
	                System.out.println(entry.getKey());
	                position = listofItems.indexOf(entry.getKey());
	                System.out.println(position);
	                actions.moveToElement(entry.getKey()).perform();
	                System.out.println("Moved");
	                //entry.getKey().click();   // Print the key with max value
	                wait.until( ExpectedConditions .visibilityOfElementLocated(By
	       				 .xpath("(//span[contains(@class,'product-wishlist')])["+(position+1)+"]"))).click();
	            }
	        }
		 
		 
		 
	}
}
