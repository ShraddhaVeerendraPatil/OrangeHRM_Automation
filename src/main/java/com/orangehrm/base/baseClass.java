package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	protected static Properties prop;
	protected WebDriver driver;

	@BeforeSuite
	public void loadconfig() throws IOException {
		// Load the config file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);

	}
	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("setting up the webdriver for :"+this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticwait(3);
		
	}

	private void launchBrowser() {
		// Intilize the brower based on browser defined in config.properties file
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Brower not supported" + browser);

		}
	}
//configure browser settings
	private void configureBrowser() {
		// Implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// Maximize the driver

		driver.manage().window().maximize();

		// Navigate to URL

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("failed to navigate to the URL:"+e.getMessage());
		}
	}


	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to quit the driver:"+e.getMessage());
			}
		}
	}
	//Static wait for pause
	public void staticwait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
