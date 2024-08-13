package com.anhtester.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderFactory {

    @DataProvider(name = "data_provider_01")
    public Object[][] dataProvider01() {
        return new Object[][]{{"First-Value"}, {"Second-Value"}, {"Third-Value"}};
    }

    @DataProvider(name = "data_provider_02")
    public Object[][] dataProvider02() {
        return new Object[][]{{"Value1", "Value2", "Value3"}, {"Value4", "Value5", "Value6"}};
    }

    @DataProvider(name = "data_provider_03", parallel = true)
    public Object[][] dataProvider03() {
        return new Object[][]{
                {"Viettel", "123456", 10, "Ha Noi"},
                {"VNPT", "56789", 5, "HCM"}
        };
    }

    @DataProvider(name = "data_provider_login", parallel = true)
    public Object[][] dataProviderLogin() {
        return new Object[][]{
                {"admin@example.com", "123456"},
                {"customer@example.com", "123456"}
        };
    }


}
