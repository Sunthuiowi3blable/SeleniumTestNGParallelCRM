package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        LogUtils.info("⭐ ********* START TESTING " + iTestContext.getName() + " *********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("⭐ ********* END TESTING " + iTestContext.getName() + " *********");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("⭐ Starting test case " + iTestResult.getName());
//        CaptureHelper.startRecord(arg0.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("✅ Test case " + iTestResult.getName() + " passedd.");
//        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("❌ Test case " + iTestResult.getName() + " failed.");
        //Lấy ra lý do lỗi
        LogUtils.error(iTestResult.getThrowable());
        CaptureHelper.screenshot(iTestResult.getName());
//        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("\uD83D\uDD1C Test case " + iTestResult.getName() + " skipped.");
//        CaptureHelper.stopRecord();
    }

}
