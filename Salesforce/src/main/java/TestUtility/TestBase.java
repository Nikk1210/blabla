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
		configProp=Util.loadProperties(currentPath+"\\src\\main\\java\\resources\\config.properties");		
		orProp=Util.loadProperties(currentPath+"\\src\\main\\java\\resources\\OR.properties");
	}

	public void intialization()
	{
		System.setProperty("webdriver.chrome.driver", currentPath+configProp.getProperty("chromedriver"));
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(configProp.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		TestBase base=new TestBase();
		base.intialization();
	}
}
