//Actiondriver class acts as a custom utility layer,designged to handle common browser interactions more effectively 
//it simplifies and enhances the webdriver functionalities by adding features like explicit wait and error handling,ensure robust and reusable code
package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class actionDriver {
	private WebDriver driver;
	private WebDriverWait wait;

	public actionDriver() {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Method to click an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to click the element:" + e.getMessage());
		}

	}

	// Method to enter text into input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to enter value in ionput box:" + e.getMessage());
		}
	}

	// Method to get text from input filed
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to get text:" + e.getMessage());
			return "";
		}

	}

	// method to compare two values
	public void compareText(By by, String expectedText) {
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (actualText.equals(expectedText)) {
				System.out.println("Text are matching:" + actualText + "Equals" + expectedText);
			} else {
				System.out.println("Text are not matching:" + actualText + "not Equals" + expectedText);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to compare the two values:" + e.getMessage());
		}

	}

	// Method to check if the element is displayed
	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			boolean isDisplayed = driver.findElement(by).isDisplayed();
			if (isDisplayed) {
				System.out.println("Element is visible:" + isDisplayed);
				return isDisplayed;
			} else {
				System.out.println("Element is not visible:");
				return isDisplayed;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Element is not visible" + e.getMessage());
			return false;
		}
	}

	// wait for element to be clickable-->Dynamic wait
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable:" + e.getMessage());
		}
	}

	// wait for element to be visible
	private void waitForElementToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("element is not visible:" + e.getMessage());
		}
	}

}