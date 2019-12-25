package pages;

import org.openqa.selenium.By;
import base.Page;

public class Homepage extends Page
{
	public LoginPage goToLogin() 
	{
		
		click("Login_XPATH");
		System.out.println("Login click successful");
		
		return new LoginPage();
	}
	
	public void goToSupport() 
	{
		driver.findElement(By.xpath("//a[@class='zh-support']")).click();
	}
	
	public void goToCustomer() 
	{
		driver.findElement(By.xpath("//a[@class='zh-customers']")).click();
	}
	
	public void goToContact() 
	{
		driver.findElement(By.xpath("//a[@class='zh-contact']")).click();
	}
	
//	public void validateFooter() 
//	{
//		driver.findElement(By.xpath("//div[@class='footer-wrapper']")).click();
//	}
}
