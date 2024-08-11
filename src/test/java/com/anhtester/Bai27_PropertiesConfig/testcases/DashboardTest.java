package com.anhtester.Bai27_PropertiesConfig.testcases;

import com.anhtester.Bai27_PropertiesConfig.pages.DashboardPage;
import com.anhtester.Bai27_PropertiesConfig.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    //Khởi tạo đối tượng class
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    //Kiểm tra xem Quick Statistics đã được chọn hay chưa
    @Test(priority = 1)
    public void testCheckSectionQuickStatisticsDisplay(){
        loginPage = new LoginPage();

        //Khi loginCRM đăng nhập xong thì nó sẽ khởi tạo mới dashboardPage
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.clickButtonDashboardOption();
        dashboardPage.verifyCheckboxQuickStatistics();
    }

    //Kiểm tra vùng Quick Statistics có đúng dữ liệu hay không
    @Test(priority = 2)
    public void testCheckTotalSectionQuickStatistics(){
        loginPage = new LoginPage();

        //Khi loginCRM đăng nhập xong thì nó sẽ khởi tạo mới dashboardPage
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);

        //dashboardPage = new DashboardPage(driver);
        dashboardPage.checkTotalInvoicesAwaitingPayment("4 / 5");
        dashboardPage.checkTotalConvertedLeads("6 / 12");
        dashboardPage.checkTotalProjectsInProgress("2 / 2");
        dashboardPage.checkTotalTasksNotFinished("21 / 21");
    }
}
