package pers.lionlinzq.excel.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class LargeExcelWriter {
    public static void main(String[] args) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Large Data");

        for (int i = 0; i < 100000; i++) { // 生成10万行数据
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) { // 每行10列
                Cell cell = row.createCell(j);
                cell.setCellValue("Data " + i + ", " + j);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("large_data.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        System.out.println("Excel 文件创建成功！");
    }
}
