package base;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import util.ExcelReader;
import util.Extent_Manager;
import util.Utilities;

public class Page
{
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fin;
	public static Logger log = Logger.getLogger("NAGARAJ");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = Extent_Manager.getInstance();
	public static ExtentTest test;
	
	public static Top_Menu menu;
	public Page() 
	{
		if(driver == null)
		{
			
			try 
			{
				
				fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
				config.load(fin);
				log.debug("Config file loaded successfully");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try 
			{
				fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
				OR.load(fin);
				log.debug("OR file loaded successfully");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		
			try
			{
				//Browser Profile Settings 
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				//Browser Selection 
				switch(config.getProperty("browser").toLowerCase())
				{
					case "chrome":
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src\\test\\resources\\Library\\Drivers\\chromedriver.exe");
						driver = new ChromeDriver(options);
						log.debug(config.getProperty("browser") + "Browser launched successfully");
						break;
					case "firefox":
						System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src\\test\\resources\\Library\\Drivers\\geckodriver.exe");
						driver = new FirefoxDriver();
						break;
					case "ie":
						System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src\\test\\resources\\Library\\Drivers\\IEDriverServer.exe");
						driver = new InternetExplorerDriver();
						break;
					default:
						System.out.println("Failed to launch" +  config.getProperty("browser") +"Browser");
						
				}
				
				
				driver.manage().window().maximize();
				driver.navigate().to(config.getProperty("url"));
				log.debug("URL loaded successfully");
				
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_timeout")), TimeUnit.SECONDS);
				wait = new WebDriverWait(driver,10);
				
				menu = new Top_Menu();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isElePresent(By by) 
	{
		try{
			driver.findElement(by);
			return true;
		}
		catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static void quit() 
	{
		driver.quit();
	}
	
	public static void click(String Locator) 
	{
		if(Locator.endsWith("_CSS")) 
		{
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).click();
		}
		else if(Locator.endsWith("_XPATH")) 
		{
			driver.findElement(By.xpath(OR.getProperty(Locator))).click();
		}
		else if(Locator.endsWith("_LinkTxt")) 
		{
			driver.findElement(By.linkText(OR.getProperty(Locator))).click();
		}
		else if(Locator.endsWith("_ID")) 
		{
			driver.findElement(By.id(OR.getProperty(Locator))).click();
		}
		
		log.debug("Successfully Clicked on " +Locator );
		test.log(LogStatus.INFO, "Clicking on : " + Locator);
	}
	
	public void type(String Locator,String Value) 
	{
		if(Locator.endsWith("_CSS")) 
		{
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).sendKeys(Value);
		}
		else if(Locator.endsWith("_XPATH")) 
		{
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(Value);
		}
		else if(Locator.endsWith("_LinkTxt")) 
		{
			driver.findElement(By.linkText(OR.getProperty(Locator))).sendKeys(Value);
		}
		else if(Locator.endsWith("_ID")) 
		{
			driver.findElement(By.id(OR.getProperty(Locator))).sendKeys(Value);
		}
		
		log.debug("Entered value as: "+Value+ " on Element: " + Locator);
		test.log(LogStatus.INFO, "Typing in : " + Locator + " Entering Values as : " + Value);
	}
	
	public void verify_txt(String actual,String expected) throws Exception 
	{
		try
		{
			Assert.assertEquals(actual, expected);
		}
		catch (Throwable t)
		{
			//ReportNG Reports
			Utilities.capture_scrrenshot();
			Reporter.log("<br>" +"<strong>Text Verification Failed: </strong>"+ t.getMessage() + " </br>");
			Reporter.log("<a target='_blank' href="+Utilities.screenshot_name+"><img height=200 width=350 src="+Utilities.screenshot_name+" + ></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//Extent Reports
			test.log(LogStatus.FAIL, "<em><strong>Text Verification Failed: </strong></em>"+ t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshot_name));
		}
	}
	
	public WebElement dropdown;
	
	public void select(String Locator,String value) 
	{
		if(Locator.endsWith("_CSS")) 
		{
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(Locator)));
		}
		else if(Locator.endsWith("_XPATH")) 
		{
			dropdown = driver.findElement(By.xpath(OR.getProperty(Locator)));
		}
		else if(Locator.endsWith("_ID")) 
		{
			dropdown = driver.findElement(By.id(OR.getProperty(Locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting the Text from Dropdown: " + Locator + " Value as: " + value);
	}
}
				
				
	


