package com.anhtester.Bai27_PropertiesConfig.testcases;

import com.anhtester.Bai27_PropertiesConfig.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.constants.ConfigData;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng trang
    private LoginPage loginPage;

    //Đăng nhập thành công
    @Test
    public void testLoginSuccess(){
        //Khởi tạo đối tượng
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();

        PropertiesHelper.setValue("ACTIVE", "false");
    }

    //Đăng nhập không thành công với email sai (không hợp lệ)
    @Test
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();

        loginPage.loginCRM("admin@example.com", "123456");
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
