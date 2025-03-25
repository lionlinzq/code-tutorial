package pers.lionlinzq.quickstartbase.demos.web;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OOMValidation {

    // 静态缓存：Key为替换后的完整SQL字符串，Value为解析后的Statement
    private static Map<String, Statement> cache = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        String sqlTemplate = "SELECT * FROM ads_${date} WHERE user_id = ${user_id}";
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        for (int j = 0; j < 32; j++) {
            int finalJ = j;
            executorService.submit(()->{
                // 模拟连续执行100,000个任务，每次替换不同变量
                for (int i = finalJ * 10000; i < 100000; i++) {
                    Map<String, String> params = new HashMap<>();
                    params.put("date", "202310" + String.format("%02d", i % 100)); // 日期变量，生成100种不同日期
                    params.put("user_id", "user_" + i); // 用户ID变量，每次不同

                    // 替换变量生成最终SQL
                    String finalSql = replaceParams(sqlTemplate, params);

                    // 解析SQL并存入缓存
                    Statement stmt = parse(finalSql);

                    // 模拟任务执行（持有Statement引用，模拟实际场景）
                    // System.out.printf("Task %d: SQL=%s, CacheSize=%d%n", i, finalSql, cache.size());

                    if (i % 100 == 0){
                        System.out.println(cache.size());
                    }
                }
            });
        }


    }

    // 变量替换方法
    private static String replaceParams(String sqlTemplate, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sqlTemplate = sqlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return sqlTemplate;
    }

    // SQL解析方法（问题代码）
    public static Statement parse(String sql) {
        String key = DigestUtils.md5Hex(sql);
        if (cache.containsKey(key)) {
            return cache.get(sql);
        }
        try {
            Statement stmt = CCJSqlParserUtil.parse(sql);
            cache.put(key, stmt);
            return stmt;
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
