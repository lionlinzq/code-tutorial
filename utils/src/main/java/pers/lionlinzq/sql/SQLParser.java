package pers.lionlinzq.sql;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.util.Assert;

@Slf4j
public class SQLParser {
    public static String parseAndUpdateStatement(String updateStatement) throws JSQLParserException {
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


    public static void main(String[] args) throws Exception {
        String updateSql = "update tablea set a = 1 where 2 = (1 + 1);";
        Statements statements = CCJSqlParserUtil.parseStatements(updateSql);
        log.info("statements is {}", statements);
        for (Statement statement : statements.getStatements()) {
            log.info("statement is {}", statement);
            processParser(statement);
        }
    }

    public static void processParser(Statement statement) {
        if (statement instanceof Insert) {
            log.debug("该语句为insert语句", statement);
        } else if (statement instanceof Select) {
            log.debug("该语句为select语句", statement);
        } else if (statement instanceof Update) {
            processUpdate((Update) statement);
        } else if (statement instanceof Delete) {
            processDelete((Delete) statement);
        } else if (statement instanceof CreateTable) {
            log.debug("该语句为create table语句", statement);
            processCreateTable((CreateTable) statement);
        } else if (statement instanceof Alter) {
            log.debug("该语句为alter table语句", statement);
            processAlter((Alter) statement);
        }
    }

    private static void processAlter(Alter alter) {

    }

    private static void processCreateTable(CreateTable createTable) {
        boolean ifNotExists = createTable.isIfNotExists();
        Assert.isTrue(ifNotExists, "建表语句需要加上if not exists");
    }


    private static void processDelete(Delete delete) {
        checkWhere(delete.getWhere(), "禁止删除全表");
    }

    private static void processUpdate(Update update) {
        String whereCondition = update.getWhere().toString();
        String replace = whereCondition.replace("=", "==");
        com.googlecode.aviator.Expression expression = AviatorEvaluator.getInstance().compile(replace);
        boolean isAlwaysTrue = (boolean) expression.execute();
        log.info("isAlwaysTrue is {}", isAlwaysTrue);
        checkWhere(update.getWhere(), "禁止更新全表");
    }

    private static void checkWhere(Expression where, String ex) {
        Assert.isTrue(!fullMatch(where), ex);
    }

    @SneakyThrows
    private static boolean fullMatch(Expression where) {
        if (where == null) {
            return true;
        }

        if (where instanceof EqualsTo) {
            // example: 1=1

            EqualsTo equalsTo = (EqualsTo) where;
            String leftExpression = equalsTo.getLeftExpression().toString();
            String rightExpression = equalsTo.getRightExpression().toString();

            return leftExpression.equals(rightExpression);
        } else if (where instanceof NotEqualsTo) {
            // example: 1 != 2
            NotEqualsTo notEqualsTo = (NotEqualsTo) where;
            return !StringUtils.equals(notEqualsTo.getLeftExpression().toString(), notEqualsTo.getRightExpression().toString());
        } else if (where instanceof OrExpression) {

            OrExpression orExpression = (OrExpression) where;
            return fullMatch(orExpression.getLeftExpression()) || fullMatch(orExpression.getRightExpression());
        } else if (where instanceof AndExpression) {

            AndExpression andExpression = (AndExpression) where;
            return fullMatch(andExpression.getLeftExpression()) && fullMatch(andExpression.getRightExpression());
        } else if (where instanceof Parenthesis) {
            // example: (1 = 1)
            Parenthesis parenthesis = (Parenthesis) where;
            return fullMatch(parenthesis.getExpression());
        }

        return false;
    }


}
