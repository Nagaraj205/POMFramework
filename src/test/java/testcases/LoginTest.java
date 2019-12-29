package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import pages.Homepage;
import pages.LoginPage;
import util.Utilities;

public class LoginTest extends BaseTest
{
	@Test(dataProviderClass=Utilities.class,dataProvider="excel_data")
	public void loginTest(Hashtable <String,String> data) throws InterruptedException 
	{
		if(data.get("runmode").equalsIgnoreCase("No")) 
		{
			System.out.println("Skipped this data as the runmode says No");
			throw new SkipException("Skipped this data as the runmode says No");
		}
		try 
		{
			Homepage home = new Homepage();
			LoginPage login = home.goToLogin();
			login.doLogin(data.get("Username"), data.get("Password"));
//			Assert.fail("Login Test Failed");
			
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
