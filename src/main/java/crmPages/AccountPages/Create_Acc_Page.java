package crmPages.AccountPages;

import base.Page;

public class Create_Acc_Page extends Page 
{
	public void create_acc(String acc_name) 
	{
		type("AccName_XPATH", acc_name);
	}
}
