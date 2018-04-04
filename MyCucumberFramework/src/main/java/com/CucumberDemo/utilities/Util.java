package com.CucumberDemo.utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

public class TestUtility {

	public static final long PAGE_LOAD_TIMEOUT=10;
	public static final long IMPLICIT_WAIT=15;
	private static Properties prop = null;
	public static FileInputStream file = null;
	private static WebDriver driver;

	public static Properties readConfig(String path) {
		prop = new Properties();
		try {
			file = new FileInputStream(path);
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	//perform activities on single element
	public static WebElement findElement(WebDriver driver, By by)
	{
		return driver.findElement(by);
	}
	
	public static void clickElement(WebDriver driver, By by)
	{
		findElement(driver, by).click();
	}
	
	public static void EnterText(WebDriver driver, By by, String text)
	{
		findElement(driver, by).clear();
		findElement(driver, by).sendKeys(text);
	}
	
	public static boolean checkIfElementDisplayed(WebDriver driver, By by)
	{
		return findElement(driver, by).isDisplayed();
	}
	
	
	//find out particular element from list & manipulate it
	public static WebElement getSpecificElement(WebDriver driver, By by, int instance)
	{
		return driver.findElements(by).get(instance);
	}
	
	public static void clickSpecificElement(WebDriver driver, By by, int instance)
	{
		getSpecificElement(driver, by, instance).click();
	}
	
	public static void EnterTextOnSpecificElement(WebDriver driver, By by, int instance, String text)
	{
		getSpecificElement(driver, by, instance).clear();
		getSpecificElement(driver, by, instance).sendKeys(text);
	}
	
	
	
	//applying wait on element
	public static void waitForElementToBeVisible(WebDriver driver, By by)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(by));   
	}
	
	
	//javascript manipulation
	public static void clickSpecificElementByJavaScript(WebDriver driver, By by, int instance)
	{
		WebElement element=getSpecificElement(driver, by, instance);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
	}
	
	//fetch page related details such as title, url
	public static String getPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}
	
	public static String getCurrentURL(WebDriver driver)
	{
		return driver.getCurrentUrl();
	}
	
}
