package TestUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class Util extends TestBase {

	private static Properties properties;
	private static FileInputStream file;
	private static XSSFWorkbook workbook;

	// utilities start from here
	// load contents of properties files
	public static Properties loadProperties(String path) {
		properties = new Properties();
		try {
			file = new FileInputStream(path);
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
			} catch (Exception e) {
			}
		}
		return properties;
	}

	public static void main(String[] args) {
		Object[][] data = fetchExcelData(currentPath + "\\src\\main\\java\\resources\\DataSource.xlsx", "LoginTest");
	}

	// fetch excel data
	public static Object[][] fetchExcelData(String path, String sheetName) {
		Object[][] cellData;
		try {
			file = new FileInputStream(path);
			workbook = new XSSFWorkbook(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		// int rowCount=sheet.getLastRowNum();
		// int cellCount=sheet.getRow(0).getLastCellNum();
		// System.out.println(rowCount+" "+cellCount);
		cellData = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		XSSFCell cell;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				cell=sheet.getRow(i).getCell(j, sheet.getRow(i).CREATE_NULL_AS_BLANK);
				if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
					cellData[i - 1][j] = "";
				else if(cell.getCellType()==(int)Cell.CELL_TYPE_NUMERIC)
					cellData[i - 1][j] = Integer.toString((int)cell.getNumericCellValue());
				else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
					cellData[i-1][j]=cell.getStringCellValue();
				
				System.out.println(i + "," + j + "->" + cellData[i - 1][j]);
			}
		}
		return cellData;
	}

	public static By fetchLocator(String object) {
		String locator = orProp.getProperty(object);
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];
		// System.out.println(locatorType + " | " + locatorValue);

		if (locatorType.equals("id"))
			return By.id(locatorValue);
		else if (locatorType.equals("name"))
			return By.name(locatorValue);
		else if (locatorType.equals("class"))
			return By.className(locatorValue);
		else if (locatorType.equals("tagname"))
			return By.tagName(locatorValue);
		else if (locatorType.equals("css"))
			return By.cssSelector(locatorValue);
		else if (locatorType.equals("xpath"))
			return By.xpath(locatorValue);
		else if (locatorType.equals("linktext"))
			return By.linkText(locatorValue);
		else if (locatorType.equals("partiallink"))
			return By.partialLinkText(locatorValue);
		else
			return null;
	}

	public static void captureScreenshot(ITestResult result)
	{
		SimpleDateFormat dateformat=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String timestamp=dateformat.format(new Date());
		String fileName=currentPath+"//Screenshots//"+result.getName()+"_"+ timestamp +".png";
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile=new File(fileName);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static WebElement findElement(String object) {
		return driver.findElement(fetchLocator(object));
	}

	public static void enterText(String locator, String text) {
		WebElement element = findElement(locator);
		element.clear();
		element.sendKeys(text);
	}

	public static void clickOnElement(String locator) {
		WebElement element = findElement(locator);
		element.click();
	}
	
	public static String getText(String locator)
	{
		WebElement element = findElement(locator);
		return element.getText();
	}

	public static List<WebElement> findElements(String object) {
		return driver.findElements(fetchLocator(object));
	}

	public static void SelectByVisibleText(String locator, String text) {
		WebElement element = findElement(locator);
		Select ele = new Select(element);
		ele.selectByVisibleText(text);
	}

	public static void applyWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void waitForFrame(String frameNameorId, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorId));
	}

	public static WebElement waitForElement(String locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = findElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public static SoftAssert softAssertTrue(boolean condition, String message) {
		SoftAssert assertion = new SoftAssert();
		assertion.assertTrue(condition, message);
		return assertion;
	}

	public static SoftAssert softAssertEquals(boolean actual, boolean expected, String message) {
		SoftAssert assertion = new SoftAssert();
		assertion.assertEquals(actual, expected, message);
		return assertion;
	}
	
	public static boolean checkIfDisplayed(String locator)
	{
		WebElement element=findElement(locator);
		return element.isDisplayed();
	}
	
	public static boolean checkIfSelected(String locator)
	{
		WebElement element=findElement(locator);
		return element.isSelected();
	}
	
	public static boolean checkIfEnabled(String locator)
	{
		WebElement element=findElement(locator);
		return element.isEnabled();
	}
}
