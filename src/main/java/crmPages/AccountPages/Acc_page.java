package crmPages.AccountPages;

import base.Page;

public class Acc_page extends Page 
{
	public Create_Acc_Page gotoCreate_acc() 
	{
		click("newAcc_XPATH");
		
		System.out.println("Create Account button click successful");
		
		return new Create_Acc_Page();
	}
	
	public void gotoImport_acc() 
	{
		
	}
}
