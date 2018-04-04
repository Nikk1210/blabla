package TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import TestUtility.TestBase;
import TestUtility.Util;

public class HomePageTest extends TestBase {

	@BeforeTest
	public void setup() {
		TestBase base = new TestBase();
		LoginTest login = new LoginTest();
		base.intialization();
		login.performLogin();
	}

	// @Test
	public void clickOnMyAccountsTab() {
		WebElement lnkAccount = Util.findElement("lnkMyAccountsTab");
		lnkAccount.click();
		Util.applyWait(1500);

		// Tag attributes changes after clicking on account tab
		List<WebElement> elements = Util.findElements("lnkMyAccountsTabSel");

		// Selected tab element was first element with class attribute in OR
		Assert.assertTrue(elements.get(0).getAttribute("title").contains("Selected"),
				"My Account tab link doesn't contain Selected text in title attribute");
	}

	// @Test
	public void clickOnMyScheduleTab() {
		WebElement lnkMySchedule = Util.findElement("lnkMyScheduleTab");
		lnkMySchedule.click();
		Util.applyWait(1500);

		// Tag attributes changes after clicking on account tab
		List<WebElement> elements = Util.findElements("lnkMyAccountsTabSel");

		// Selected tab element was first element with class attribute in OR
		Assert.assertTrue(elements.get(0).getAttribute("title").contains("Selected"),
				"My Schedule tab link doesn't contain Selected text in title attribute");
	}

	@DataProvider(name = "SearchTestData")
	public Object[][] getSearchTestData() {
		Object[][] data = Util.fetchExcelData(dataFile, "searchTest");
		return data;
	}

	public void searchAndLogin(String searchText) {
		Util.enterText("txtSearch", searchText);
		Util.clickOnElement("btnSearch");
		Util.clickOnElement("btnSearchUserLogin");
		System.out.println("Login button clicked");
	}

	public void clickOnProfessional() {
		Util.clickOnElement("lnkMyAccountsTabSel");
		Util.waitForFrame("itarget", 15);
		System.out.println("frame switched");
//		WebElement userInstance = 
				Util.waitForElement("lnkSearchusername", 12).click();
//		userInstance.click();
				
	}
	
	/*Record types are 
	aHUS - HCO
	aHUS - HCP	 
	HPP - HCO	 
	HPP - HCP	 
	LAL-D - HCO	 
	LAL-D - HCP	 
	PNH - HCO	 
	PNH - HCP	 
	ref gMG / gMG - HCO	 
	ref gMG - HCP*/
	public void addHCPHCODetails(String recordType)
	{
		Util.clickOnElement("btnHCOHCPProfessional");
		Util.SelectByVisibleText("drpRecordType", recordType);
		Util.clickOnElement("btnHCPContinue");
		Util.assertTrue(Util.checkIfDisplayed("lblPNHOnTherapy"), "PNH On Therapy label not present");
		
	}

	@Test(dataProvider = "SearchTestData")
	public void performSearch(String searchText) {
		searchAndLogin(searchText);
		clickOnProfessional();
	}

	// @AfterMethod
	public void tearDown() {
		driver.close();
	}
}
