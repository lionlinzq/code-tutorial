package utils.http;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil {

    public static void autoColWidth(Sheet sheet){
        int colnum = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i < colnum; i++){
            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(colnum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(colnum) != null) {
                    Cell currentCell = currentRow.getCell(colnum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(colnum, columnWidth * 256);
        }
    }

}
