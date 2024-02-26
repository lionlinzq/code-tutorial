package pers.lionlinzq.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * Excel监听程序
 *
 * @author lin
 * @date 2024/01/31
 */
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private ExcelBaseService excelBaseService;

    public ExcelListener() {
    }

    public ExcelListener(ExcelBaseService excelBaseService) {
        this.excelBaseService = excelBaseService;
    }

    /**
     * 每隔1000条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<T> list = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
        log.info("解析到一条数据:{}", JSON.toJSONString(data));

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }


    /**
     * 返回list
     */
    public List<T> getData() {
        return this.list;
    }

}


