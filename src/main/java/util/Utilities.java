package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;



import base.Page;

public class Utilities extends Page
{
	public static String screenshot_path;
	public static String screenshot_name;
	
	public static void capture_scrrenshot() throws IOException 
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		
		screenshot_name = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		screenshot_path = System.getProperty("user.dir") + "\\target\\surefire-reports\\html/" + screenshot_name;
		FileUtils.copyFile(scrFile, new File(screenshot_path));
	}
	
	@DataProvider(name="excel_data")
 	public Object[][] getData(Method m) 
	{
		String sheetname = m.getName();
		int rows = excel.getRowCount(sheetname);
		int cols = excel.getColumnCount(sheetname);
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String,String> table = null;
		for(int rownum = 2;rownum<=rows;rownum++) 
		{
			table = new Hashtable<String,String>();
			for(int colnum=0;colnum<cols;colnum++) 
			{
				table.put(excel.getCellData(sheetname, colnum, 1), excel.getCellData(sheetname, colnum, rownum));
				data[rownum-2][0] = table;
			}
		}
		return data;
		
	}

	public static boolean isTestRunnable(String test_name,ExcelReader excel) 
	{
		String sheet_name = "test_suite";
		int rows = excel.getRowCount(sheet_name);
		for(int rownum = 2;rownum<=rows;rownum++) 
		{
			String Test_case = excel.getCellData(sheet_name, "TCID", rownum);
			
			if(Test_case.equalsIgnoreCase(test_name))
			{
				String Runnable = excel.getCellData(sheet_name, "RunMode", rownum); 
				if(Runnable.equalsIgnoreCase("Yes")) 
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
			
		}
		return false;
	}
}
