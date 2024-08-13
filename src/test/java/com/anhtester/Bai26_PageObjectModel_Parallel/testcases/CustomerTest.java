package com.anhtester.Bai26_PageObjectModel_Parallel.testcases;

import com.anhtester.Bai26_PageObjectModel_Parallel.pages.*;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {

    CommonPage commonPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @Test
    public void testAddNewCustomer(){

        String CUSTOMER_NAME = "An_Customer_01";
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Customer");

        loginPage = new LoginPage();

        //Khi loginCRM đăng nhập xong thì nó sẽ chuyển đến dashboardPage
        dashboardPage = loginPage.loginCRM(ConfigData.EMAIL, ConfigData.PASSWORD);

        //Vì trang loginPage kế thừa (extend) lại trang commonPage nên ở đây loginPage cũng . gọi được clickMenuCustomer sau đấy chuyển đến trang customerPage
        customerPage = dashboardPage.clickMenuCustomer(); //Hàm này nằm bên CommonPage

        //Lưu dữ liệu Total Customer ban đầu, Integer.parseInt(customerPage.getTotalCustomers()): thực hiện chuyển đổi chuỗi chứa số lượng khách hàng thành một số nguyên và trả về giá trị số nguyên này từ phương thức getTotalCustomers().
        int totalCustomersBefore = Integer.parseInt(customerPage.getTotalCustomers());

        //In dữ diệu của Total Customers ban đầu
        System.out.println("▫\uFE0FTotal Customer Before: " + totalCustomersBefore);

        customerPage.clickAddNewButton();
        customerPage.enterDataAddNewCustomer(CUSTOMER_NAME, 2);
        customerPage.checkCustomerInTableList(excelHelper.getCellData("CUSTOMER_NAME", 2));

        //In dữ liệu của Total Customers sau khi thêm mới
        System.out.println("▫\uFE0FTotal Customer After: " + customerPage.getTotalCustomers());

        //String.valueOf là một phương thức tĩnh trong lớp String của Java, được sử dụng để chuyển đổi các loại dữ liệu khác thành một chuỗi (String).
        Assert.assertEquals(customerPage.getTotalCustomers(), String.valueOf(totalCustomersBefore + 1), "FAIL!! The Total Customers in Customer Page not match.");
        customerPage.checkCustomerDetail(excelHelper.getCellData("CUSTOMER_NAME", 2));

        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInSelectSection(excelHelper.getCellData("CUSTOMER_NAME", 2));
    }
}
