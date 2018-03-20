package com.CucumberDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.CucumberDemo.utilities.TestBase;
import com.CucumberDemo.utilities.TestUtility;

public class LoginPage extends TestBase {

	//page library is arranged using By class since multiple elements were having same locators
	// Alternative xpaths are also provided in comments


	By lnkAccount = By.xpath("//a[contains(text(),'My Account')]"); // b[contains(@class,'lightcaret
																	// mt-2')]
	By lnkLogin = By.xpath("//a[contains(text(),' Login')]"); // a[contains(.,'Login')]
	By lnkSignUp = By.xpath("//a[contains(text(),'Sign Up')]");
	By txtEmail = By.name("username");
	By txtPassword = By.name("password");
	By btnLogin=By.xpath("//button[contains(text(),'Login')]");

	public LoginPage() {
		super();
	}

	public void clickOnLogin() 
	{
		TestUtility.clickSpecificElementByJavaScript(driver, lnkAccount, 1);
		TestUtility.clickSpecificElementByJavaScript(driver, lnkLogin, 2);
	}

	public void clickOnSignUp() 
	{
		TestUtility.clickSpecificElementByJavaScript(driver, lnkAccount, 1);
		TestUtility.clickSpecificElementByJavaScript(driver, lnkSignUp, 1);
	}
	
	public String getTitle()
	{
		return TestUtility.getPageTitle(driver);
	}
	
	public AccountPage performLogin(WebDriver driver, String email, String password)
	{
		TestUtility.EnterText(driver, txtEmail, email);
		TestUtility.EnterText(driver, txtPassword, password);
		TestUtility.clickElement(driver, btnLogin);
		return new AccountPage();
	}

}
