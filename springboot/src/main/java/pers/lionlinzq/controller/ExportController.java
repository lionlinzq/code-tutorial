package pers.lionlinzq.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.lionlinzq.domain.User;
import pers.lionlinzq.service.UserService;
import pers.lionlinzq.utils.ExcelBaseService;
import pers.lionlinzq.utils.LocalDateTimeConverter;
import pers.lionlinzq.utils.easyexcel.MultiColumnMergeStrategy;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    ExcelBaseService excelService;

    @Autowired
    UserService userService;


    @PostMapping("/importExcel")
    public void importExcel(@RequestParam MultipartFile file) {
        excelService.importExcel(file, User.class, 2, userService::saveBatch);
    }


    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("data", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xls");

        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("id");
        excludeColumnFiledNames.add("createTime");

        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("createTime");


        int[] mergeColumnIndex = {0, 1};
        // 需要从第几行开始合并
        int mergeRowIndex = 1;

        EasyExcel.write(response.getOutputStream())
                .head(User.class).automaticMergeHead(false)
                .excelType(ExcelTypeEnum.XLSX)
                .registerWriteHandler(new MultiColumnMergeStrategy(userService.list().size(), 0, 1))
                // .registerWriteHandler(new ExcelFillCellMergeStrategy(mergeRowIndex,mergeColumnIndex))
                // .excludeColumnFieldNames(excludeColumnFiledNames)
                // .includeColumnFieldNames(includeColumnFiledNames)
                .registerConverter(new LocalDateTimeConverter())
                .sheet("工作簿1")
                .doWrite(userService.list());

        // excelService.exportExcel(userService.list(),User.class,response);
    }


}
