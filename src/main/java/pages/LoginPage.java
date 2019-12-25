package pages;

import base.Page;

public class LoginPage extends Page
{
	
	public Zoho_Apppage doLogin(String Username,String Password) 
	{
		type("email_CSS", Username);
		click("signin_XPATH");
		
		type("password_CSS", Password);
		click("signin_XPATH");
		System.out.println("login succesfull");
		
		return new Zoho_Apppage();
	}
}
