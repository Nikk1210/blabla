package TestUtility;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	public static String currentPath=System.getProperty("user.dir");
	public static String dataFile=currentPath+"\\src\\main\\java\\resources\\DataSource.xlsx";
	public static Properties configProp, orProp;
	public static WebDriver driver;
	
	public TestBase()
	{
		configProp=Utilities.loadProperties(currentPath+"\\src\\main\\java\\resources\\config.properties");		
		orProp=Utilities.loadProperties(currentPath+"\\src\\main\\java\\resources\\OR.properties");
	}

	public void intialization()
	{
		System.setProperty("webdriver.chrome.driver", currentPath+configProp.getProperty("chromedriver"));
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(configProp.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	}
}
