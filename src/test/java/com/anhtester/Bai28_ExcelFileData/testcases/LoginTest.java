package com.anhtester.Bai28_ExcelFileData.testcases;

import com.anhtester.Bai28_ExcelFileData.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng trang
    private LoginPage loginPage;

    //Đăng nhập thành công
    @Test
    public void testLoginSuccess(){
        //Khởi tạo đối tượng
        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet3");

        for (int i = 1; i <=1; i++) {
            loginPage.loginCRM(
                    excelHelper.getCellData("EMAIL", i),
                    excelHelper.getCellData("PASSWORD", i)
            );
            loginPage.verifyLoginSuccess();
            loginPage.logout();
        }
        excelHelper.setCellData("Passed", "STATUS", 1);
    }

    //Đăng nhập không thành công với email sai (không hợp lệ)
    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("Passed", "STATUS", 1);
    }

    //Đăng nhập không thành công với password sai (không hợp lệ)
    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM(
                excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3)
        );
        loginPage.verifyLoginFail("Invalid email or password");
        excelHelper.setCellData("Passed", "STATUS", 2);
    }

    //Đăng nhập không thành công với email để trống
    @Test
    public void testLoginFailWithEmailNull(){
        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM("", "123");
        loginPage.verifyLoginFail("The Email Address field is required.");
        excelHelper.setCellData("Passed", "STATUS", 3);
    }

    //Đăng nhập không thành công với password để trống
    @Test
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage();

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
        WebUI.sleep(1);
        excelHelper.setCellData("Passed", "STATUS", 4);
    }
}
