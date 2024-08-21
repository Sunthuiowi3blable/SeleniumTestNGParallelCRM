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

        //Nếu giá trị "RECORD_VIDEO" = true thì nó sẽ record video, bằng false thì không record video
        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
                    CaptureHelper.startRecord(iTestResult.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("✅ Test case " + iTestResult.getName() + " passedd.");

        //Nếu giá trị "SCREENSHOT_STEP_PASS" = true thì nó sẽ screenshot, bằng false thì không screenshot
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")){
            CaptureHelper.screenshot(iTestResult.getName());
        }

        //Nếu giá trị "RECORD_VIDEO" = true thì nó sẽ record video, bằng false thì không record video
        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")) {
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("❌ Test case " + iTestResult.getName() + " failed.");
        //Lấy ra lý do lỗi
        LogUtils.error(iTestResult.getThrowable());

        //Nếu giá trị "SCREENSHOT_STEP_FAIL" = true thì nó sẽ screenshot, bằng false thì không screenshot
        if (PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")){
            CaptureHelper.screenshot(iTestResult.getName());
        }

        //Nếu giá trị "RECORD_VIDEO" = true thì nó sẽ record video, bằng false thì không record video
        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")) {
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("\uD83D\uDD1C Test case " + iTestResult.getName() + " skipped.");

        //Nếu giá trị "RECORD_VIDEO" = true thì nó sẽ record video, bằng false thì không record video
        if (PropertiesHelper.getValue("RECORD_VIDEO").equals("true")) {
            CaptureHelper.stopRecord();
        }
    }
}
