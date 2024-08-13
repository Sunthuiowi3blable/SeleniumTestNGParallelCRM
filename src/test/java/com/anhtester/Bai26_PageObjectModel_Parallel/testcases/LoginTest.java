package com.anhtester.Bai26_PageObjectModel_Parallel.testcases;

import com.anhtester.Bai26_PageObjectModel_Parallel.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng trang
    private LoginPage loginPage;

    //Đăng nhập thành công
    //Đăng nhập thành công
    @Test
    public void testLoginSuccess(){
        //Khởi tạo đối tượng
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
    }

    //Đăng nhập không thành công với email sai (không hợp lệ)
    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    //Đăng nhập không thành công với password sai (không hợp lệ)
    @Test
    public void testLoginFailWithPasswordInvalid(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "123");
        loginPage.verifyLoginFail("Invalid email or password");
    }

    //Đăng nhập không thành công với email để trống
    @Test
    public void testLoginFailWithEmailNull(){
        loginPage = new LoginPage();

        loginPage.loginCRM("", "123");
        loginPage.verifyLoginFail("The Email Address field is required.");
    }

    //Đăng nhập không thành công với password để trống
    @Test
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFail("The Password field is required.");
    }
}
