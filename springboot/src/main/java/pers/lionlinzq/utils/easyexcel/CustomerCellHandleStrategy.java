package pers.lionlinzq.utils.easyexcel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;

public class CustomerCellHandleStrategy extends AbstractMergeStrategy {
    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex) {

        // 当前单元格向上合并
        // 获取当前行的当前列的数据和上一行的当前列列数据，通过上一行数据是否相同进行合并
        Object curData = cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.getNumericCellValue();
        Cell preCell = cell.getSheet().getRow(relativeRowIndex - 1).getCell(cell.getColumnIndex());
        Object preData = preCell.getCellType() == CellType.STRING ? preCell.getStringCellValue() : preCell.getNumericCellValue();
    }
}
