package com.hellofresh.utils;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.RollingFileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Enumeration;

public class CustomRunListener implements ITestListener, ISuiteListener, IInvokedMethodListener {
    private static String testClass;

    public static String getTestClass() {
        return testClass;
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }

    @Override
    public final void onStart(ISuite iSuite) {
        Logger logger = LoggerFactory.getLogger(CustomRunListener.class);
        testClass = iSuite.getAllMethods().get(0).getTestClass().getName();
        org.apache.log4j.Logger rootLogger = org.apache.log4j.Logger.getRootLogger();
        Enumeration appenders = rootLogger.getAllAppenders();
        FileAppender fa = null;
        while(appenders.hasMoreElements()) {
            Appender currAppender = (Appender) appenders.nextElement();
            if(currAppender instanceof FileAppender){
                fa = (RollingFileAppender) currAppender;
            }
        }
        if(fa != null){
            fa.setFile("target/logs/" + getTestClass() + ".log");
            fa.activateOptions();
        } else {
            logger.error("No file log apender found");
        }
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public final void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public final void onTestFailure(ITestResult iTestResult) {
        Logger logger = LoggerFactory.getLogger(CustomRunListener.class);
        logger.error("Test failed: " + iTestResult.getInstanceName(), iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public final void onFinish(ITestContext iTestContext) {

    }
}
