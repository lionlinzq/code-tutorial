package pers.lionlinzq.sql;

import java.util.regex.Pattern;

public class SQLValidator {
    private static final Pattern SELECT_PATTERN = Pattern.compile("^\\s*SELECT", Pattern.CASE_INSENSITIVE);
    private static final Pattern INSERT_PATTERN = Pattern.compile("^\\s*INSERT", Pattern.CASE_INSENSITIVE);

    public static boolean isSafeSQL(String sql) {
        String trimmedSql = sql.trim();
        return SELECT_PATTERN.matcher(trimmedSql).find() || INSERT_PATTERN.matcher(trimmedSql).find();
    }

    public static void main(String[] args) {
        String sql = "select   drop * from table";
        System.out.println(isSafeSQL(sql));
    }
}