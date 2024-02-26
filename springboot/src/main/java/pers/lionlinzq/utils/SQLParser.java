package pers.lionlinzq.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    }

}
