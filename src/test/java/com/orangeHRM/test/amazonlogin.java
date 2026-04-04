package com.orangeHRM.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazonlogin {

	public static void main(String[] args) {
		
		//path for chromedriver
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20)) ;
		
		driver.get("https://www.amazon.in");
		
		WebElement searchBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
		searchBox.sendKeys("iphone 15");
		 
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		//click on 1st prod
		
		WebElement firstProduct=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-component-type='s-search-result']//h2/a)[1]")));
		firstProduct.click();
		
		String mainwindow=driver.getWindowHandle();
		Set<String>allwindows=driver.getWindowHandles();
		
		for (String window:allwindows)
		{
			if(!window.equals(allwindows)) {
				driver.switchTo().window(window);
				break;
			}
		}
		WebElement addtocart=wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
		addtocart.click();
		System.out.println("Product added to cart");
		
		driver.quit();
	}

}
