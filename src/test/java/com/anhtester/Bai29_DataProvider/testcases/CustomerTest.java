package com.anhtester.Bai29_DataProvider.testcases;

import com.anhtester.Bai29_DataProvider.pages.*;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.helpers.ExcelHelper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CustomerTest extends BaseTest {

    CommonPage commonPage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @Test (dataProvider = "data_provider_login_excel_hashtable_customer", dataProviderClass = DataProviderFactory.class)
    public void testAddNewCustomer(Hashtable<String, String> data){

        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/ExcelData.xlsx", "LoginDataProvider");

        dashboardPage = loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );

        //Vì trang loginPage kế thừa (extend) lại trang commonPage nên ở đây loginPage cũng . gọi được clickMenuCustomer sau đấy chuyển đến trang customerPage
        customerPage = dashboardPage.clickMenuCustomer(); //Hàm này nằm bên CommonPage

        //Lưu dữ liệu Total Customer ban đầu, Integer.parseInt(customerPage.getTotalCustomers()): thực hiện chuyển đổi chuỗi chứa số lượng khách hàng thành một số nguyên và trả về giá trị số nguyên này từ phương thức getTotalCustomers().
        int totalCustomersBefore = Integer.parseInt(customerPage.getTotalCustomers());

        //In dữ diệu của Total Customers ban đầu
        System.out.println("▫\uFE0FTotal Customer Before: " + totalCustomersBefore);

        customerPage.clickAddNewButton();
        customerPage.enterDataAddNewCustomer(data);
        customerPage.checkCustomerInTableList(data);

        //In dữ liệu của Total Customers sau khi thêm mới
        System.out.println("▫\uFE0FTotal Customer After: " + customerPage.getTotalCustomers());

        //String.valueOf là một phương thức tĩnh trong lớp String của Java, được sử dụng để chuyển đổi các loại dữ liệu khác thành một chuỗi (String).
        Assert.assertEquals(customerPage.getTotalCustomers(), String.valueOf(totalCustomersBefore + 1), "FAIL!! The Total Customers in Customer Page not match.");
        customerPage.checkCustomerDetail(data);

        projectPage = customerPage.clickMenuProjects();
        projectPage.clickAddNewProject();
        projectPage.checkCustomerDisplayInSelectSection(data.get("CUSTOMER_NAME"));
    }
}
