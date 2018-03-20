package com.CucumberDemo.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase extends TestUtility 
{
	private String currentPath = System.getProperty("user.dir");
	public static WebDriver driver=null;
	public static Properties prop=null;

	public TestBase()
	{
		prop=readConfig("C://Users//Atishkadu//eclipse-workspace//MyCucumberFramework//src//main//java//com//CucumberDemo//config//config.properties");
	}
	
	public void Initialize()
	{
//		startBrowsingSession();
	}
	
	
	public static void startBrowsingSession(String url, String browser)
	{
//		String browser=prop.getProperty("browser");		
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
			driver=new ChromeDriver();
			break;
		case "gecko":
			System.setProperty("webdriver.gecko.driver", prop.getProperty("geckodriver"));
			driver=new FirefoxDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", prop.getProperty("iedriver"));
			driver=new InternetExplorerDriver();
			break;
		default:
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(TestUtility.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtility.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}
	
public static void main(String[] args) {
	TestBase t=new TestBase();
//System.out.println(prop.getProperty("browser"));
	t.startBrowsingSession(prop.getProperty("browser"), prop.getProperty("url"));
}	

}
