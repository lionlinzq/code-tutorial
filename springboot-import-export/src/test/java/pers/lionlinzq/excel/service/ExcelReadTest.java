package pers.lionlinzq.excel.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
public class ExcelReadTest {

    public static final String path = "./users.xlsx";

    @Test
    public void openExcel() throws IOException {
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        
        File file = new File(path);
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workbook = new XSSFWorkbook(fis);
            int numberOfSheets = workbook.getNumberOfSheets();
            log.info("excel共有{}个sheet",numberOfSheets);
        }
    }

}
