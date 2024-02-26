package pers.lionlinzq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lionlinzq.utils.SQLParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author lin
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SQLParser sqlParser;

    private final static ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping(value = "/get")
    public List<Map<String, Object>> get(String updateStatement) throws JSQLParserException {
        sqlParser.parseSelectStatement(updateStatement);
        String selectStatement = sqlParser.parseAndUpdateStatement(updateStatement);
        List<Map<String, Object>> data = sqlParser.getData(updateStatement);

        String fileName = "./" + LocalDateTime.now() + "日志.txt";
        // 在文件夹目录下新建文件
        File file = new File(fileName);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    log.info("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write("原先初始更新语句:\n" + updateStatement);
            // 换行
            osw.write("\r\n\n");
            osw.write("转换后的查询语句:\n" + selectStatement);
            osw.write("\r\n\n");
            osw.write("解析后的数据为:\n" + objectMapper.writeValueAsString(data));
            log.info("成功向文件 [{}] 写入内容", fileName);
        } catch (Exception e) {
            log.info("写入文件发生异常", e);
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.info("关闭流异常", e);
            }
        }

        return data;
    }

}



