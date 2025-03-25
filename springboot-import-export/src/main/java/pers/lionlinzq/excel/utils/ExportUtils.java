package pers.lionlinzq.excel.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pers.lionlinzq.excel.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExportUtils {

    public static void export(List<User> list, List<String> rows, HttpServletResponse response) {
        // 创建excel
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 创建sheet
        Sheet sheet = workbook.createSheet();

        // 创建表头
        Row title = sheet.createRow(0);
        int i = 0;
        for (String row : rows) {
            title.createCell(i++).setCellValue(row);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 填充数据
        int rowNum = 1;
        for (User user : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getPassword());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(user.getPhone());
            row.createCell(5).setCellValue(user.getStatus());
            String formatTime = user.getCreatedAt().format(formatter);
            row.createCell(6).setCellValue(formatTime);
        }

        // 创建表头和填充数据...
        // 设置响应头
        response.setHeader("Content-Disposition", "attachment; filename=userinfo" + System.currentTimeMillis() + ".xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // 写入响应流
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close();
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static List<User> upload(InputStream inputStream) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<User> list = new ArrayList<>();
        int cols;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i + 1); // 表头不算
            cols = 0;
            User user = User.builder()
                    .id(Long.parseLong("" + (long) row.getCell(cols++).getNumericCellValue()))
                    .username(row.getCell(cols++).getStringCellValue())
                    .password(row.getCell(cols++).getStringCellValue())
                    .email(row.getCell(cols++).getStringCellValue())
                    .phone(row.getCell(cols++).getStringCellValue())
                    .status((int) row.getCell(cols++).getNumericCellValue())
                    .createdAt(LocalDateTime.parse(row.getCell(cols++).getStringCellValue(),formatter))
                    .build();

            list.add(user);
            log.info("user:{}",user.toString());
        }
        workbook.close();
        return list;
    }

}
