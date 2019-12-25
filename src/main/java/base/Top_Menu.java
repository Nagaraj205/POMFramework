package base;

import crmPages.AccountPages.Acc_page;

public class Top_Menu 
{
	public void gotoHomeTab() 
	{
		
	}
	
	public void gotoLeadTab() 
	{
		
	}
	
	public void gotoContactsTab() 
	{
		
	}
	
	public Acc_page gotoAccountsTab() 
	{
		Page.click("Account_LinkTxt");
		System.out.println("Accounts tab click successful");
		
		return new Acc_page();
	}
	
	public void gotoDealsTab() 
	{
		
	}
	
	public void SingOut() 
	{
		
	}
}
