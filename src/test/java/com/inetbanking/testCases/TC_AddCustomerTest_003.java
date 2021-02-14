package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	public void AddNewCustomer() throws InterruptedException, IOException 
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.ClickAddNewCustomer();
		logger.info("Providing Customer Details.....");
		addcust.custName("vipin");
		addcust.custgender("male");
		addcust.custdob("15", "04", "1989");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("Azamgarh");
		addcust.custstate("UP");
		addcust.custpinno("276001");
		addcust.custtelephoneno("9450200830");
		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		logger.info("Validation Started Here....  ");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test Case Pass...");
			
		}
		else
		{
			logger.info("Test Case Failed...");
			captureScreen(driver,"AddNewCustomer");
			Assert.assertTrue(false);
		}
	}
	public String randomstring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}
	public static String RandomNum()
	{
		String generatedstring2=RandomStringUtils.randomNumeric(4);
		return (generatedstring2);
	}
}
