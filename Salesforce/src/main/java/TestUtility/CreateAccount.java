package TestUtility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateAccount {


		public static void main(String[] args) {
			
			//code to iterate through properties file
			/*Enumeration config=configProp.propertyNames();
			while(config.hasMoreElements())
			{
				String key=(String)config.nextElement();
				String value=configProp.getProperty(key);
				System.out.println(key +" \t||\t "+ value);
			}*/
			// TODO Auto-generated method stub

	       /* System.setProperty("webdriver.ie.driver", "//clds-ctrxfs01/citrixdata/JAINH3241/Downloads/Libarary/IEDriverServer.exe");
	        WebDriver driver = new InternetExplorerDriver();
	        */
	        System.setProperty("webdriver.chrome.driver", "//clds-ctrxfs01/citrixdata/JAINH3241/Downloads/Libarary/chromedriver.exe");
	        WebDriver driver = new ChromeDriver();   
	        
	        
	        driver.manage().window().maximize();
	    
	        
	        driver.get("https://test.salesforce.com");
	        
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.findElement(By.id("username")).click();
	        driver.findElement(By.id("username")).sendKeys("dhananjay.salaskar-consultant@alexion.com.crm.qa");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("password")).sendKeys("Dj_1608dj");
	        driver.findElement(By.id("Login")).click();
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.findElement(By.id("01r36000000amGq_Tab")).click();
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.switchTo().frame("itarget");
	        System.out.println("Swtich to frame");
	        driver.findElement(By.xpath("//input[@name='newAcctButton']")).click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("recordTypeSelect")).click();
	        
	        Select fruits = new Select(driver.findElement(By.id("recordTypeSelect")));
	        fruits.selectByVisibleText("Professional");
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.findElement(By.xpath("//*[@class=\"Continue btn\"][@value=\"Continue\"]")).click();
	        
	        driver.findElement(By.xpath("//*[@class=\"soundex btn\"][@value=\"Continue\"]")).click();
	        
	       /* String newAccountexpectedText = "Error: Invalid Data.";
	        
	        WebElement msg = driver.findElement(By.id("errorDiv"));
	        String text=msg.getText();
	        
	       */
	      
	        driver.findElement(By.id("i-value:picklist:Account:Salutation")).click();
	        Select nameTitle = new Select (driver.findElement(By.id("i-value:picklist:Account:Salutation")));
	        nameTitle.selectByVisibleText("Mr.");
	        
	        driver.findElement(By.id("reqreqi-value:string:Account:FirstName")).sendKeys("Test");
	        driver.findElement(By.id("reqreqi-value:string:Account:LastName")).sendKeys("Test");
	               
	        driver.findElement(By.xpath("//*[@id=\"A1\"]/img")).click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("parentAccountSearchInput")).sendKeys("Test");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("parentAccountSearchButton")).click();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	        List<WebElement> listElement=driver.findElements(By.xpath("//span[contains(text(),'test')]"));
	        listElement.get(0).click();
	        
	        //WebElement abc = driver.findElement(By.xpath("//iframe[@name='itarget']"));
	        driver.switchTo().frame("itarget");
	        
	        System.out.println("Frame New 1");
	      
	        driver.findElement(By.xpath("//*[@value=\"Continue\"][1]")).click();
	        driver.findElement(By.xpath("//*[@value=\"Continue\"][1]")).click();
	        
	        //driver.findElement(By.xpath("//*[@id=\"pbHeaderTableRow\"]/td[2]/input[2]")).click();

	        //driver.findElement(By.xpath("//*[@class=\"preview btn\"][@value=\"Continue\"]")).click();
	     
	        
	     
	        
		}


}
