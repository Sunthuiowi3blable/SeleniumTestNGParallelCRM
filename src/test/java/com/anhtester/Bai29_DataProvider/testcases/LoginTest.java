package com.anhtester.Bai29_DataProvider.testcases;

import com.anhtester.Bai29_DataProvider.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.dataproviders.DataProviderFactory;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng trang
    private LoginPage loginPage;

    //Đăng nhập thành công
    @Test (dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccess(String email, String password){
        //Khởi tạo đối tượng
        loginPage = new LoginPage();

        loginPage.loginCRM(email, password);
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
