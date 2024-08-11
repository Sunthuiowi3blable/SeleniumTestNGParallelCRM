package com.anhtester.Bai28_ExcelFileData;

import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcelFile {
    @Test
    public void getDataFromExcel(){

        //Khai báo đối tượng class
        ExcelHelper excelHelper = new ExcelHelper();

        //Đọc Data
        excelHelper.setExcelFile("src/test/resources/testdata/Login.xlsx", "Sheet1");

        //Get SIMPLE (đơn) data from Excel (Lấy data trong file Excel theo từng ô chỉ định)
        System.out.println(excelHelper.getCellData("EMAIL", 1));
        System.out.println(excelHelper.getCellData("PASSWORD", 1));
        System.out.println("*********************************************");

        //**Get MUTIPLE (đa) data from Excel (Lấy data trong file Excel theo từng ô chỉ định)**
        //Muốn đọc nhiều dòng 1 lúc thì dùng vòng lặp for
        for (int i = 1; i <=3; i++) {
            System.out.println(excelHelper.getCellData("EMAIL", i));
            System.out.println(excelHelper.getCellData("PASSWORD", i));
        }
        System.out.println("*********************************************");

        //**Set data to Excel (Thêm giá trị chỉ đinh vào ô Excel chỉ định)**
        excelHelper.setCellData("Passed", "STATUS", 1);
        excelHelper.setCellData("Failed", "STATUS", 2);

    }
}
