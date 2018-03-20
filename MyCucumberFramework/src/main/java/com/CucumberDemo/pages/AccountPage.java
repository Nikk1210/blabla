package com.CucumberDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.CucumberDemo.utilities.TestBase;
import com.CucumberDemo.utilities.TestUtility;

public class AccountPage extends TestBase {
	By BookingsSection=By.xpath("//span[contains(@class,'bookings')]");
	
	public AccountPage()
	{
		super();
	}
	
	public boolean checkSuccessfulLogin()
	{
		return TestUtility.checkIfElementDisplayed(driver, BookingsSection);
	}
	
}
