package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user ,String psw) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username is provided");
		lp.setPassword(psw);
		logger.info("Password is provided");
		lp.clickSubmit();
		Thread.sleep(3000);

		if(IsAlertPresent()==true)
		{
			driver.switchTo().alert().accept();//Close Alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login Passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//Close logout Alert
			driver.switchTo().defaultContent();
		}
	}

	public boolean IsAlertPresent() //User defined method created to check to alert is present or not

	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}

	@DataProvider(name="LoginData")
	String [][]getdata() throws IOException
	{
		String path=System.getProperty("user.dir")+"src/test/java/com/inetbanking/testData/TestData.xlsx";

		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int celcount=XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][]=new String[rownum][celcount];

		for(int i=1;i<rownum;i++) 
		{
			for(int j=0;j<celcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i,j);//1 0
			}
		}
		return logindata;
	}

}
