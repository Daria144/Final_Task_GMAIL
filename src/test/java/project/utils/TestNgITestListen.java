package project.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgITestListen implements ITestListener
{
    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        Log4j2Manager.info("==============="+"Logging started for"+" "+result.getMethod().getMethodName()+"==================");
        Log4j2Manager.info(result.getMethod().getMethodName()+":"+"STARTED");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        Log4j2Manager.info(result.getMethod().getMethodName()+":"+"PASSED");
        Log4j2Manager.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        Log4j2Manager.info(result.getMethod().getMethodName()+":"+"FAILED");
        Log4j2Manager.error("Falied error thrown", result.getThrowable());
        Log4j2Manager.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        Log4j2Manager.info(result.getMethod().getMethodName()+":"+"SKIPPED");
        Log4j2Manager.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        //Log4j2Manager.info("================"+" "+"Started logging"+" "+context.getName()+" "+"================");
    }
    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        //Log4j2Manager.info("================"+" "+"Ended logging"+" "+context.getName()+" "+"================");
    }

}
