package pers.lionlinzq.sql;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Slf4j
public class SQLParserTest {


    public String selectStatement = "SELECT column1, column2 FROM table_name WHERE column3 = value3 and column4 = value4;";

    public String updateStatement = "UPDATE table_name SET column1 = value1,column2 = value2 WHERE column3 = value3 and column4 = value4;";

    @Test
    public void testParseAndUpdateStatement() throws JSQLParserException {
        assertEquals("result", SQLParser.parseAndUpdateStatement("updateStatement"));
    }

    @Test
    public void testMain() throws Exception {
        // Setup
        // Run the test
        SQLParser.main(new String[]{"args"});

        // Verify the results
    }

    @Test
    public void testMain_ThrowsException() {
        // Setup
        // Run the test
        assertThrows(Exception.class, () -> SQLParser.main(new String[]{"args"}));
    }

    @Test
    public void testParseUpdateStatement() throws JSQLParserException {
        ;
        Statement statement = CCJSqlParserUtil.parse(updateStatement);
        if (statement instanceof Update) {
            Update update = (Update) statement;
            Expression whereCause = update.getWhere();
            log.info("where is {}", whereCause);

            String tableName = update.getTable().getName();

            String selectStatement = "select * from" + tableName + "where" + whereCause;

            log.info("selectStatement is {}", selectStatement);
        }
    }

    @Test
    public void testGetData() throws JSQLParserException {
        String string = "[{id=1, name=Jone, age=18, email=test1@baomidou.com, create_time=2024-01-31 22:40:53.0}, {id=2, name=Jack, age=18, email=test2@baomidou.com, create_time=2024-01-31 22:41:01.0}, {id=3, name=Tom, age=18, email=test3@baomidou.com, create_time=2024-01-31 22:41:04.0}, {id=4, name=Sandy, age=18, email=test4@baomidou.com, create_time=2024-01-31 22:41:06.0}]";
        JSONArray objects = JSON.parseArray(string);
        log.info("objects is {}", objects);
    }

    @Test
    public void testWhere() {
        // 定义 SQL 中的 WHERE 条件
        String whereCondition = "2 = (1 + 1) and c = 3 and d = 'a=aqesa'";

        // 将 SQL 中的条件表达式中的 "=" 替换为 Aviator 支持的 "=="
        whereCondition = whereCondition.replaceAll("\\b(\\d+)\\s*=\\s*\\(", "$1 == (");

        // 使用 Aviator 解析 WHERE 条件
        com.googlecode.aviator.Expression expression = AviatorEvaluator.getInstance().compile(whereCondition);

        // 对 WHERE 条件进行求值
        boolean isAlwaysTrue = (boolean) expression.execute();

        // 输出结果
        if (isAlwaysTrue) {
            System.out.println("WHERE 条件恒为真");
        } else {
            System.out.println("WHERE 条件不恒为真");
        }

    }
}
