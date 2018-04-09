package Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {
	private int retryCount=0;
	private int maxRetryCount=2;
	
	public boolean retry(ITestResult result) {
		String testName=result.getName();
		int testStatus=result.getStatus();
		
		if(retryCount<maxRetryCount)
		{
			System.out.println("Test failed is "+ testName +" with status "+getResult(testStatus)
					+ ", Retrying the test "+testName+" for "+(++retryCount)+" time");
			return true;
		}
		return false;
	}
	
	public String getResult(int status)
	{
		String testResult=null;
		
		if(status==1)
			testResult="Passed";
		else if(status==2)
			testResult="Fail";
		return testResult;
			
	}

}
