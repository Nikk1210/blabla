package TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import TestUtility.TestBase;
import TestUtility.Utilities;

public class HomePageTest extends TestBase {

	@BeforeTest
	public void setup()
	{
		TestBase base=new TestBase();
		LoginTest login=new LoginTest();
		base.intialization();
		login.performLogin();
	}

//	@Test
	public void clickOnMyAccountsTab()
	{
		WebElement lnkAccount=Utilities.findElement("lnkMyAccountsTab");
		lnkAccount.click();
		Utilities.applyWait(1500);
		
		//Tag attributes changes after clicking on account tab
		List<WebElement> elements=Utilities.findElements("lnkMyAccountsTabSel");
		
		//Selected tab element was first element with class attribute in OR
		Assert.assertTrue(elements.get(0).getAttribute("title").contains("Selected"), 
						"My Account tab link doesn't contain Selected text in title attribute");
	}
	
	
//	@Test
	public void clickOnMyScheduleTab()
	{
		WebElement lnkMySchedule=Utilities.findElement("lnkMyScheduleTab");
		lnkMySchedule.click();
		Utilities.applyWait(1500);
		
		//Tag attributes changes after clicking on account tab
		List<WebElement> elements=Utilities.findElements("lnkMyAccountsTabSel");
		
		//Selected tab element was first element with class attribute in OR
		Assert.assertTrue(elements.get(0).getAttribute("title").contains("Selected"), 
						"My Schedule tab link doesn't contain Selected text in title attribute");
	}
	
	@DataProvider(name="SearchTestData")
	public Object[][] getSearchTestData()
	{
		Object[][] data=Utilities.fetchExcelData(dataFile,"searchTest");
		return data;
	}
	
	
	@Test(dataProvider="SearchTestData")
	public void performSearch(String searchText)
	{
		Utilities.enterText("txtSearch", searchText);
		Utilities.clickOnElement("btnSearch");
		Utilities.applyWait(1500);
	}
	
//	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
}
