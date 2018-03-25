package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;

import TestUtility.TestBase;
import TestUtility.Utilities;

public class LoginTest extends TestBase {
	
	@BeforeMethod
	public void setup()
	{
		TestBase base=new TestBase();
		base.intialization();
	}
	
//	@Test
	public void checkPageTitle()
	{
		String title=driver.getTitle();
		Assert.assertEquals(title, "Login | Salesforce", "title of page is not valid");
	}
	
//	@Test
	public void checkElementsPresence()
	{
		boolean elementsPresent=Utilities.findElement("txtUsername").isDisplayed() &&
				Utilities.findElement("txtPassword").isDisplayed();
		Assert.assertTrue(elementsPresent);
	}
	
	@Test
	public void performLogin()
	{
		Utilities.findElement("txtUsername").sendKeys(configProp.getProperty("username"));
		Utilities.findElement("txtPassword").sendKeys(configProp.getProperty("password"));
		Utilities.findElement("btnLogin").click();
		Utilities.applyWait(1500);
		String title=driver.getTitle();
		Assert.assertEquals(title, "Salesforce - Unlimited Edition", "title of home page is not valid");
	}
	
	
	//1st iteration is negative login test
//	@Test(dataProvider="LoginTestData")
	public void dataDrivenLogin(String username, String password)
	{
		Utilities.findElement("txtUsername").sendKeys(username);
		Utilities.findElement("txtPassword").sendKeys(password);
		Utilities.findElement("btnLogin").click();
		Utilities.applyWait(1500);
		String title=driver.getTitle();
		Assert.assertEquals(title, "Salesforce - Unlimited Edition", "title of home page is not valid");
	}
	
	@DataProvider(name="LoginTestData")
	public Object[][] getLoginTestData()
	{
		Object[][] data=Utilities.fetchExcelData(dataFile,"LoginTest");
		return data;
	}
	
//	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
