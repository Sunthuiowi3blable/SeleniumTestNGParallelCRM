package com.anhtester.Bai28_ExcelFileData.pages;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.ExcelHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.anhtester.keywords.WebUI.*;

public class CustomerPage extends CommonPage {

    public CustomerPage() {
    }

    //Khai báo các element trên trang Customer đó dạng đối tượng By (đối tượng By là đối tượng mới chỉ thiết lập cấu trúc chứ chưa tìm kiếm)
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By headerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@placeholder='Search...']");
    private By firstItemCustomerName = By.xpath("//tbody/tr[1]/td[3]/a");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVat = By.xpath("//input[@id='vat']");
    private By inputPhone = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By selectGroup = By.xpath("//button[@data-id='groups_in[]']");
    private By inputGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By selectLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemVietnam = By.xpath("//span[normalize-space()='Vietnamese']");
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By selectCountry = By.xpath("//button[@data-id='country']");
    private By inputCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By alertMessage = By.xpath("//span[@class='alert-title']");
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");

    // **Hàm xử lý cho trang Customer**
    //Hàm click chọn nút Add New Customer Button
    public void clickAddNewButton(){
        clickElement(buttonAddNewCustomer);
    }

    //Hàm lấy giá trị Total Customers
    public String getTotalCustomers(){
        waitForPageLoaded();
        return getElementText(totalCustomers);
    }

    //Hàm xử lý thay đổi text trong xpath
    public void selectlanguage(String languageName){
        clickElement(selectLanguage);
        clickElement(By.xpath("//span[normalize-space()='" + languageName + "']"));
    }

    //Hàm nhập thông tin trong trang sau khi ấn vào nút Add New Customer
    public void enterDataAddNewCustomer(String customerName, int row){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Customer");

        setText(inputCompany, excelHelper.getCellData("CUSTOMER_NAME", row));
        setText(inputVat, excelHelper.getCellData("VAT", row));
        setText(inputPhone, excelHelper.getCellData("PHONE", row));
        setText(inputWebsite, excelHelper.getCellData("WEBSITE", row));
        clickElement(selectGroup);
        sleep(1);
        setText(inputGroup, "VIP");
        sleep(1);
        setKey(inputGroup, Keys.ENTER);
        sleep(1);
        clickElement(selectGroup);
        selectlanguage("Vietnamese");
        sleep(2);

        setText(inputAddress, "Ha Noi");
        setText(inputCity, "Ha Noi");
        setText(inputState, "Ha Noi");
        setText(inputZip, "12345");

        clickElement(selectCountry);
        sleep(1);
        setText(inputCountry, "VietNam");
        sleep(1);
        setKey(selectCountry, Keys.ENTER);
        sleep(1);
        clickElement(buttonSave);
        WebUI.waitForPageLoaded();

        Assert.assertTrue(checkElementExist(alertMessage), "\uD83D\uDC1E FAIL!! The alert message success not display.");
        //.trim() là bỏ qua khoảng trắng lấy mỗi text
        Assert.assertEquals(DriverManager.getDriver().findElement(alertMessage).getText().trim(), "Customer added successfully.", "\uD83D\uDC1E FAIL!! The content of alert message not match.");
    }

    //Kiểm tra bằng việc tìm kiếm phần tử vừa Add và gettext giá trị vừa thêm ra
    public void checkCustomerInTableList(String customerName){
        waitForPageLoaded();
        clickElement(menuCustomers);
        waitForPageLoaded();
        setText(inputSearchCustomer, customerName);
        waitForPageLoaded();
        sleep(2);

        //Check customer name display in table
        Assert.assertTrue(checkElementExist(firstItemCustomerName), "\uD83D\uDC1E FAIL!! The customer name not display in table.");
        //Assert.assertEquals(driver.findElement(firstItemCustomerName).getText(), customerName, "\uD83D\uDC1E FAIL!! The customer name not match.");

        assertEquals(getElementText(firstItemCustomerName), customerName, "\uD83D\uDC1E FAIL!! The customer name not match.");

    }

    //Kiểm tra laại giá trị các thông tin được add vào
    public void checkCustomerDetail(String customerName){
        waitForPageLoaded();
        clickElement(firstItemCustomerName);
        waitForPageLoaded();

        //Check customer detail in Customer Detail page
        assertEquals(getElementAttribute(inputCompany, "value"), customerName, "FAIL!! The Company name not match.");
        assertEquals(getElementAttribute(inputVat,"value"), "20", "FAIL!! The VAT value not match.");
        assertEquals(getElementAttribute(inputPhone,"value"), "56789", "FAIL!! The Phone value not match.");
        assertEquals(getElementAttribute(inputWebsite, "value"), "https://anhtester.com", "FAIL!! The Website value not match.");
        assertEquals(getElementAttribute(selectGroup,"title"), "VIP", "FAIL!! The Group of customer not match.");
        assertEquals(getElementAttribute(selectLanguage,"title"), "Vietnamese", "FAIL!! The Language value not match.");
        assertEquals(getElementAttribute(inputAddress,"value"), "Can Tho", "FAIL!! The Address value not match.");
    }
}
