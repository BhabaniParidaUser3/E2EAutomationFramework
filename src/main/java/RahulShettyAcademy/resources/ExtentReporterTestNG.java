package RahulShettyAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {
	
	
	public static ExtentReports getReportObject()
	{
		//ExtentReports,ExtentSparkReporter
				String path=System.getProperty("user.dir")+"\\reports\\index.html";
				ExtentSparkReporter reporter=new ExtentSparkReporter(path);
				reporter.config().setReportName("WebAutomationResult");
				reporter.config().setDocumentTitle("TestResults");
				
				ExtentReports extent=new ExtentReports();  
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Bhabani Parida");
				return extent;
	}
}
