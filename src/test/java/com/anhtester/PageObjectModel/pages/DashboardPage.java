package com.anhtester.PageObjectModel.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage extends CommonPage {

    SoftAssert softAssert = new SoftAssert();

    //Khai báo hàm xây dựng
    public DashboardPage() {
    }

    //Khai báo các element trên trang Dashboard đó dạng đối tượng By (đối tượng By là đối tượng mới chỉ thiết lập cấu trúc chứ chưa tìm kiếm)
    private By buttonDashboardOptions = By.xpath("//div[normalize-space()='Dashboard Options']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");
    private By totalProjectsInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");
    private By checkboxQuickStatistics = By.xpath("//input[@id='widget_option_top_stats']");
    private By sectionQuickStatistics = By.xpath("//div[@id='widget-top_stats']");

    //Hàm xử lý cho các Element trên
    public void clickButtonDashboardOption(){
        //Đợi trang load xong
        WebUI.waitForPageLoaded();
        System.out.println(WebUI.checkElementExist(buttonDashboardOptions));
        WebUI.clickElement(buttonDashboardOptions);
    }

    public void verifyCheckboxQuickStatistics(){
        WebUI.sleep(1);
        //Kiểm tra phần tử có được tick hay chưa
        softAssert.assertTrue(DriverManager.getDriver().findElement(checkboxQuickStatistics).isSelected(), "FAIL!! The value of checkbox Quick Statistics not match.");
        //Kiểm tra phần tử có được hiển thị hay không
        softAssert.assertTrue(DriverManager.getDriver().findElement(sectionQuickStatistics).isDisplayed(), "FAIL!! The section Quick Statistics not display.");
    }

    public void checkTotalInvoicesAwaitingPayment(String value){
        SoftAssert softAssert = new SoftAssert();
        //Đợi trang load xong
        WebUI.waitForPageLoaded();
        //Check xem phần tử có tồn tại không
        softAssert.assertTrue(WebUI.checkElementExist(totalInvoicesAwaitingPayment), "The section Invoices Awaiting Payment not display.");
        //Check xem text có đúng không
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalInvoicesAwaitingPayment).getText(), value, "FAIL!! Invoices Awaiting Payment total not match");
    }

    public void checkTotalConvertedLeads(String value){
        //Đợi trang load xong
        WebUI.waitForPageLoaded();
        //Check xem phần tử có tồn tại không
        softAssert.assertTrue(WebUI.checkElementExist(totalConvertedLeads), "The section Invoices Awaiting Payment not display.");
        //Check xem text có đúng không
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalConvertedLeads).getText(), value, "FAIL!! Invoices Awaiting Payment total not match");
    }

    public void checkTotalProjectsInProgress(String value){
        //Đợi trang load xong
        WebUI.waitForPageLoaded();
        //Check xem phần tử có tồn tại không
        softAssert.assertTrue(WebUI.checkElementExist(totalProjectsInProgress), "The section Invoices Awaiting Payment not display.");
        //Check xem text có đúng không
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalProjectsInProgress).getText(), value, "FAIL!! Invoices Awaiting Payment total not match");
    }

    public void checkTotalTasksNotFinished(String value){
        //Đợi trang load xong
        WebUI.waitForPageLoaded();
        //Check xem phần tử có tồn tại không
        softAssert.assertTrue(WebUI.checkElementExist(totalTasksNotFinished), "The section Invoices Awaiting Payment not display.");
        //Check xem text có đúng không
        softAssert.assertEquals(DriverManager.getDriver().findElement(totalTasksNotFinished).getText(), value, "FAIL!! Invoices Awaiting Payment total not match");

        softAssert.assertAll();
    }
}
