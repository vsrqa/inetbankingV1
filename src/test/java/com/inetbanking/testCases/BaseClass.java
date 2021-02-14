package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	/*
	 * public String baseURL="http://demo.guru99.com/v3/index.php"; public String
	 * username="mngr288059"; public String password="qEjaqet";
	 */
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	public static WebDriver driver;
	public static Logger logger;
	
    @Parameters("browser")
	@BeforeClass
	public void setup(String br) {
    	
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	@AfterClass
	public void teardown() 
	{
		driver.quit();
	}
//Capture screenshots	
public void captureScreen(WebDriver driver, String tname) throws IOException {
TakesScreenshot ts=(TakesScreenshot)driver;
File Source =ts.getScreenshotAs(OutputType.FILE);
File Target=new File(System.getProperty("user.dir") + "/ScreenShots/"+ tname +".png");
FileUtils.copyFile(Source, Target);
System.out.println("ScreenShot taken");

}
}

