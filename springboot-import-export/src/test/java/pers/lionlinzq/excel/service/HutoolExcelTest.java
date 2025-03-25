package pers.lionlinzq.excel.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HutoolExcelTest {

    public static void main(String[] args) {
        ExcelWriter writer = ExcelUtil.getWriter("./large_data.xlsx");
        for (int i = 0; i < writer.getPhysicalRowCount(); i++) {
            int currentRow = writer.getCurrentRow();
            log.info("currentRow:{}",currentRow);
        }
    }

}
