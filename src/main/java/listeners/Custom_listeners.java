package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.relevantcodes.extentreports.LogStatus;


import base.Page;
import util.Utilities;

public class Custom_listeners extends Page implements ITestListener

{

	@Override
	public void onFinish(ITestContext arg0) {
		
	}

	@Override
	public void onStart(ITestContext Test_case)
	{
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	@Override
	public void onTestFailure(ITestResult Test_case) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try
		{
			Utilities.capture_scrrenshot();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, Test_case.getName().toUpperCase() + " <string>FAIL</strong> " + Test_case.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshot_name));
		
		Reporter.log(Utilities.screenshot_name);
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href="+Utilities.screenshot_name+"><img height=200 width=350 src="+Utilities.screenshot_name+" + ></img></a>");
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onTestSkipped(ITestResult tc)
	{
		test.log(LogStatus.SKIP, tc.getName() + " Skipped this test_case as run_mode says No");
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult Test_case) {
		
		test = report.startTest(Test_case.getName().toUpperCase() + " ");
	}

	@Override
	public void onTestSuccess(ITestResult Test_case)
	{
		test.log(LogStatus.PASS, Test_case.getName().toUpperCase() + " PASS ");
		report.endTest(test);
		report.flush();
	}

}
