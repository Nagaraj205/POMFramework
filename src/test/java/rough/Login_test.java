package rough;

import base.Page;
import crmPages.AccountPages.Acc_page;
import crmPages.AccountPages.Create_Acc_Page;
import pages.Homepage;
import pages.LoginPage;
import pages.Zoho_Apppage;

public class Login_test
{
	
	public static void main(String[] agrs) throws InterruptedException 
	{
		//This is a Rough Package
		Homepage home = new Homepage();
		LoginPage login = home.goToLogin();
		Zoho_Apppage zoho = login.doLogin("nagaraj.g.work@gmail.com", "Zoho@123#");
		zoho.gotoCRM();
		Acc_page acc = Page.menu.gotoAccountsTab();
		Create_Acc_Page create = acc.gotoCreate_acc();
		create.create_acc("Nagaraj G");
		Thread.sleep(2000);
		
		Page.driver.quit();
		
	}
}
