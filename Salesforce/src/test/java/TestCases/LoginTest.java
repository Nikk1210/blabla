package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;

import TestUtility.TestBase;
import TestUtility.Util;

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
		boolean elementsPresent=Util.findElement("txtUsername").isDisplayed() &&
				Util.findElement("txtPassword").isDisplayed();
		Assert.assertTrue(elementsPresent);
	}
	
	@Test
	public void performLogin()
	{
		Util.findElement("txtUsername").sendKeys(configProp.getProperty("username"));
		Util.findElement("txtPassword").sendKeys(configProp.getProperty("password"));
		Util.findElement("btnLogin").click();
		Util.applyWait(1500);
		String title=driver.getTitle();
		Assert.assertEquals(title, "Salesforce - Unlimited Edition", "title of home page is not valid");
	}
	
	
	//1st iteration is negative login test
//	@Test(dataProvider="LoginTestData")
	public void dataDrivenLogin(String username, String password)
	{
		Util.findElement("txtUsername").sendKeys(username);
		Util.findElement("txtPassword").sendKeys(password);
		Util.findElement("btnLogin").click();
		Util.applyWait(1500);
		String title=driver.getTitle();
		Assert.assertEquals(title, "Salesforce - Unlimited Edition", "title of home page is not valid");
	}
	
	@DataProvider(name="LoginTestData")
	public Object[][] getLoginTestData()
	{
		Object[][] data=Util.fetchExcelData(dataFile,"LoginTest");
		return data;
	}
	
//	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
