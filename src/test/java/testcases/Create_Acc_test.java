package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.Page;
import crmPages.AccountPages.Acc_page;
import crmPages.AccountPages.Create_Acc_Page;
import pages.Zoho_Apppage;
import util.Utilities;

public class Create_Acc_test extends BaseTest
{
	@Test(dataProviderClass=Utilities.class,dataProvider="excel_data")
	public void create_acc_test(Hashtable<String,String> data)  throws InterruptedException
	{
		if(data.get("runmode").equalsIgnoreCase("No")) 
		{
			System.out.println("Skipped this data as the runmode says No");
			throw new SkipException("Skipped this data as the runmode says No");
			
		}
		try 
		{
			Zoho_Apppage zoho = new Zoho_Apppage();
			zoho.gotoCRM();
			Acc_page acc = Page.menu.gotoAccountsTab();
			Create_Acc_Page create = acc.gotoCreate_acc();
			create.create_acc(data.get("AccountName"));
			Assert.fail("Failed t create Account");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
