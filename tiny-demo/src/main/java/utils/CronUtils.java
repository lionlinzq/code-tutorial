package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cron工具
 *
 * @since: 2022/10/10
 * @author: hongdahao
 */
public class CronUtils {

    private static final Logger logger = LoggerFactory.getLogger(CronUtils.class);

    public static void main(String[] args) {
        System.out.println(CronUtils.translateToChinese("10 * * ? * * *"));
    }


    private static void generalTranslate(StringBuffer resultSb, String subExp, String unit){
        if(unit.equals("周")){
            subExp = subExp
                .replaceAll("1","一")
                .replaceAll("2","二")
                .replaceAll("3","三")
                .replaceAll("4","四")
                .replaceAll("5","五")
                .replaceAll("6","六")
                .replaceAll("7","日");
            if(subExp.contains(",")){
                resultSb.append("当周").append(subExp.replaceAll(",","、"));
            }else if(subExp.contains("-")){
                resultSb.append("周").append(subExp.split("-")[0]).append("至周").append(subExp.split("-")[1]);
            }else if(subExp.contains("*/")){
                resultSb.append("从周一开始每").append(subExp.split("/")[1].replaceAll("日","七")).append("日");
            }else if(subExp.contains("/")){
                resultSb.append("从周").append(subExp.split("/")[0]).append("开始")
                        .append("每").append(subExp.split("/")[1].replaceAll("日","七")).append("日");
            }else if(subExp.contains("*")){
                resultSb.append("每日");
            }else {
                resultSb.append("每周").append(subExp);
            }
        }else{
            if(subExp.contains(",")){
                resultSb.append("当").append(subExp.replaceAll(",","、")).append(unit);
            }else if(subExp.contains("-")){
                resultSb.append(subExp.split("-")[0]).append("至").append(subExp.split("-")[1]).append(unit);
            }else if(subExp.contains("*/")){
                resultSb.append("每").append(subExp.split("/")[1]).append(unit);
            }else if(subExp.contains("/")){
                resultSb.append("从").append(subExp.split("/")[0]).append(unit).append("开始")
                        .append("每").append(subExp.split("/")[1]).append(unit);
            }else if(subExp.contains("*")){
                resultSb.append("每").append(unit);
            }else {
                resultSb.append(subExp).append(unit);
            }
        }
    }


    public static String translateToChinese(String cronExp) {
        try {
            //复杂的cron直接返回
            if (cronExp.contains("L") || cronExp.contains("W") || cronExp.contains("C") || cronExp.contains("#")) {
                return cronExp;
            }
            String[] cronUnits = cronExp.split(" ");
            StringBuffer resultSb = new StringBuffer();
            //解析年（如果有）
            if (cronUnits.length == 7) {
                generalTranslate(resultSb, cronUnits[6], "年");
            }else{
                resultSb.append("每年");
            }
            //解析月
            generalTranslate(resultSb, cronUnits[4], "月");
            //解析日或周
            if (cronUnits[3].equals("?")) {
                //解析周
                generalTranslate(resultSb, cronUnits[5], "周");
            } else {
                //解析日
                generalTranslate(resultSb, cronUnits[3], "日");
            }
            //解析时分秒
            generalTranslate(resultSb, cronUnits[2], "时");
            generalTranslate(resultSb, cronUnits[1], "分");
            generalTranslate(resultSb, cronUnits[0], "秒");
            return resultSb.append("执行").toString();
        } catch (Exception e) {
            logger.error("翻译cron时出错:" + cronExp,  e);
            return cronExp;
        }
    }

}
