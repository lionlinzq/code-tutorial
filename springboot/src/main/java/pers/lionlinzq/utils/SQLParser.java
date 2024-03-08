package pers.lionlinzq.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Component
public class SQLParser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String parseAndUpdateStatement(String updateStatement) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(updateStatement);
        String selectStatement = "";
        if (statement instanceof Update) {
            Update update = (Update) statement;
            Expression whereCause = update.getWhere();
            log.info("where is {}", whereCause);

            String tableName = update.getTable().getName();

            selectStatement = "select * from " + tableName + " where " + whereCause;

            log.info("selectStatement is {}", selectStatement);
        }
        return selectStatement;
    }

    public List<Map<String, Object>> getData(String updateStatement) throws JSQLParserException {
        String selectStatement = parseAndUpdateStatement(updateStatement);
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(selectStatement);
        log.info("maps is {}", maps);
        return maps;
    }

    public void parseSelectStatement(String selectStatement) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(selectStatement);
        String tableName = "";
        if (statement instanceof Select) {

        } else if (statement instanceof Update) {
            Update update = (Update) statement;
            tableName = update.getTable().getName();
        } else if (statement instanceof Delete) {
            Select select = (Select) statement;
            tableName = select.getSelectBody().toString();
        } else {
            throw new RuntimeException("不支持的语句类型");
        }
    }


    public static void main(String[] args) throws Exception {
        // 指定要遍历的目录
        String directoryPath = "C:\\Code_Work\\code-tutorial\\springboot\\src\\main\\resources";
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> tables = new ArrayList<>();

        // 遍历目录并读取SQL文件
        try {
            Files.walk(Paths.get(directoryPath)).forEach(filePath -> {
                if (filePath.toFile().getName().endsWith(".sql")) {// 读取SQL文件内容
                    String fileContent = null;
                    try {
                        fileContent = new String(Files.readAllBytes(filePath), "utf-8");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(filePath.toFile().getName());
                    Statements statements = null;
                    try {
                        statements = CCJSqlParserUtil.parseStatements(fileContent);
                    } catch (JSQLParserException e) {
                        throw new RuntimeException(e);
                    }
                    for (Statement statement : statements) {
                        if (statement instanceof Insert) {
                            Table table = ((Insert) statement).getTable();
                            System.out.println(table.getName());
                        }
                    }

                }

            });
        }catch (Exception e){
            throw e;
        }


    }

}
