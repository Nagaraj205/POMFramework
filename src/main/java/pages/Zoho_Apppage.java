package pages;

import org.openqa.selenium.By;
import base.Page;

public class Zoho_Apppage extends Page
{
	
	public void gotoBooks() 
	{
		driver.findElement(By.xpath("//div[contains(text(),'Books')]")).click();
	}
	
	public void gotoClaender() 
	{
		driver.findElement(By.xpath("//div[contains(text(),'Calendar')]")).click();
	}
	
	public void gotoCampaigns() 
	{
		driver.findElement(By.xpath("//div[contains(text(),'Campaigns')]")).click();
	}
	
	public void gotoCRM() 
	{
		click("CRM_XPATH");
		System.out.println("successfully clicked on CRM");
	}
	
}
