package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("⭐ onStart");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("⭐ onFinish");
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("⭐ onTestStart");
        CaptureHelper.startRecord(arg0.getName());
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("✅ onTestSuccess");
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        System.out.println("❌ onTestFailure");
        CaptureHelper.screenshot(arg0.getName());
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("\uD83D\uDD1C onTestSkipped");
        CaptureHelper.stopRecord();
    }

}
