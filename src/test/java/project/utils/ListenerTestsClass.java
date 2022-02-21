package test.java.project.utils;
import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.java.project.context.LoginContext;

public class ListenerTestsClass implements ITestListener {
    public static Logger log= Logger.getLogger(LoginContext.class);
    @Override
    public void onTestStart(ITestResult result)
    {
        // TODO Auto-generated method stub
        log.info("==============="+"Logging started for"+" "+result.getMethod().getMethodName()+"==================");
        log.info(result.getMethod().getMethodName()+":"+"STARTED");
    }
    @Override
    public void onTestSuccess(ITestResult result)
    {
        // TODO Auto-generated method stub
        log.info(result.getMethod().getMethodName()+":"+"PASSED");
        log.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
    @Override
    public void onTestFailure(ITestResult result)
    {
        // TODO Auto-generated method stub
        log.info(result.getMethod().getMethodName()+":"+"FAILED");
        log.error("Falied error thrown", result.getThrowable());
        log.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
    @Override
    public void onTestSkipped(ITestResult result)
    {
        // TODO Auto-generated method stub
        log.info(result.getMethod().getMethodName()+":"+"SKIPPED");
        log.info("==============="+"Logging ended for"+" "+result.getMethod().getMethodName()+"==================");

    }
}
