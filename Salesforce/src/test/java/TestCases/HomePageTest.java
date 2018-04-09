package TestCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.*;

import TestUtility.*;

public class HomePageTest extends TestBase {
SoftAssert assertion;
	
	@BeforeMethod
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
				Util.waitForElement("lnkSearchusername", 20).click();
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
	public SoftAssert addHCPHCODetails(String recordType)
	{
		Util.clickOnElement("btnHCOHCPProfessional");
		Util.SelectByVisibleText("drpRecordType", recordType);
		Util.clickOnElement("btnHCPContinue");
		return assertControls(recordType);
	}
	
	public SoftAssert assertControls(String recordType)
	{
		assertion=new SoftAssert();
		if(recordType.equals("aHUS-HCO"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSOnTherapy"), "aHUS On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSNeverOnTherapy"), "aHUS Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSStopped"), "aHUSStopped label not present");
		}
		else if(recordType.equals("aHUS - HCP"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSOnTherapy"), "aHUS On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSNeverOnTherapy"), "aHUS Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblaHUSMeetCriteria"), "aHUS Meet criteria label not present");
		}
		else if(recordType.equals("HPP - HCO"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblHPPOnTherapy"), "HPP On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblHPPNeverOnTherapy"), "HPP Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblHPPStopped"), "HPP Meet criteria label not present");
		}
		else if(recordType.equals("HPP - HCP"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblHPPOnTherapy"), "HPP On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblHPPNeverOnTherapy"), "HPP Never On Therapy label not present");
		}
		
		else if(recordType.equals("LAL-D - HCO"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblLALDOnTherapy"), "LAL-D On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblLALDNeverOnTherapy"), "LAL-D Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblLALDStopped"), "LAL-D Stopped label not present");
		}
		else if(recordType.equals("LAL-D - HCP"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblLALDOnTherapy"), "LAL-D On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblLALDNeverOnTherapy"), "LAL-D Never On Therapy label not present");
		}
		else if(recordType.equals("PNH-HCO"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHOnTherapy"), "PNH On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHNeverOnTherapy"), "PNH Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHStopped"), "PNH stopped label not present");
		}
		else if(recordType.equals("PNH-HCP"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHOnTherapy"), "PNH On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHNeverOnTherapy"), "PNH Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblPNHMeetCriteria"), "PNH Meet Criteria label not present");
		}
		else if(recordType.equals("ref gMG / gMG - HCO"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblgMGOnTherapy"), "gMG On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblgMGNeverOnTherapy"), "gMG Never On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblgMGStopped"), "gMG Stopped label not present");
		}
		else if(recordType.equals("ref gMG - HCP"))
		{
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblgMGOnTherapy"), "gMG On Therapy label not present");
			assertion=Util.softAssertTrue(Util.checkIfDisplayed("lblgMGNeverOnTherapy"), "gMG Never On Therapy label not present");
		}
		
		return assertion;
	}

	public boolean enterAggregatePatientData(String pnhNeverOnTherapy, String meetCriteria)
	{
		Util.enterText("txtPNHNeverOnTherapy", pnhNeverOnTherapy);
		Util.enterText("txtPNHMeetCriteria", meetCriteria);
		Util.findElements("btnSaveHCPHCO").get(1).click();
		System.out.println(Util.getText("lblHCPNo"));
		return Util.checkIfDisplayed("lblHCPNo");
	}
	
	@Test(dataProvider = "SearchTestData")
	public void performSearch(String searchText, String recordType, String PNHOnDemand, String PNHNeverOnTherapy, String meetCriteria) {
		searchAndLogin(searchText);
		clickOnProfessional();
		assertion=addHCPHCODetails(recordType);
		assertion.assertAll();
	}

	
//	@Test(dataProvider = "SearchTestData")
	public void enterPatientData(String searchText, String recordType, String pnhNeverOnTherapy, String meetCriteria) {
		searchAndLogin(searchText);
		clickOnProfessional();
		addHCPHCODetails(recordType);
		Assert.assertTrue(enterAggregatePatientData(pnhNeverOnTherapy, meetCriteria),"Profile number not generated..");
	}
	
	 @AfterMethod
	public void tearDown(ITestResult result) {
		Util.captureScreenshot(result);
		driver.close();
	}
}
