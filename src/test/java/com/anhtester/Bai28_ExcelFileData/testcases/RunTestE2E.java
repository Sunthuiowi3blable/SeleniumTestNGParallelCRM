package com.anhtester.Bai28_ExcelFileData.testcases;

import com.anhtester.common.BaseTestE2E;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RunTestE2E extends BaseTestE2E {

    //Đăng nhập thành công
    @Test (priority = 1)
    public void testLoginSuccess(){

        getLoginPage().loginCRM("admin@example.com", "123456");
        getLoginPage().verifyLoginSuccess();
    }

    //Kiểm tra xem Quick Statistics đã được chọn hay chưa
    @Test(priority = 2)
    public void testCheckSectionQuickStatisticsDisplay(){
        //dashboardPage = new DashboardPage(driver);
        getDashboardPage().clickButtonDashboardOption();
        getDashboardPage().verifyCheckboxQuickStatistics();
    }

    @Test (priority = 3)
    public void testAddNewCustomer(){

        String CUSTOMER_NAME = "An_Customer_02";

        //Vì trang loginPage kế thừa (extend) lại trang commonPage nên ở đây loginPage cũng . gọi được clickMenuCustomer sau đấy chuyển đến trang customerPage
        getDashboardPage().clickMenuCustomer(); //Hàm này nằm bên CommonPage

        //Lưu dữ liệu Total Customer ban đầu, Integer.parseInt(customerPage.getTotalCustomers()): thực hiện chuyển đổi chuỗi chứa số lượng khách hàng thành một số nguyên và trả về giá trị số nguyên này từ phương thức getTotalCustomers().
        int totalCustomersBefore = Integer.parseInt(getCustomerPage().getTotalCustomers());

        //In dữ diệu của Total Customers ban đầu
        System.out.println("▫\uFE0FTotal Customer Before: " + totalCustomersBefore);

        getCustomerPage().clickAddNewButton();
        getCustomerPage().enterDataAddNewCustomer(CUSTOMER_NAME, 1);
        getCustomerPage().checkCustomerInTableList(CUSTOMER_NAME);

        //In dữ liệu của Total Customers sau khi thêm mới
        System.out.println("▫\uFE0FTotal Customer After: " + getCustomerPage().getTotalCustomers());

        //String.valueOf là một phương thức tĩnh trong lớp String của Java, được sử dụng để chuyển đổi các loại dữ liệu khác thành một chuỗi (String).
        Assert.assertEquals(getCustomerPage().getTotalCustomers(), String.valueOf(totalCustomersBefore + 1), "FAIL!! The Total Customers in Customer Page not match.");
        getCustomerPage().checkCustomerDetail(CUSTOMER_NAME);

        getCustomerPage().clickMenuProjects();
        getProjectPage().clickAddNewProject();
        getProjectPage().checkCustomerDisplayInSelectSection(CUSTOMER_NAME);
    }
}
