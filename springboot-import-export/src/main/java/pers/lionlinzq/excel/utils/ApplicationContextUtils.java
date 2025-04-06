package pers.lionlinzq.excel.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationContextUtils {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 静态的容器对象
     */
    private static ApplicationContext staticApplicationContext;

    @PostConstruct
    public void init(){
        ApplicationContextUtils.staticApplicationContext = applicationContext;
    }

    /**
     * 通过类型获得Bean
     * @param c
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> c){
        if (staticApplicationContext != null) {
            return staticApplicationContext.getBean(c);
        }
        return null;
    }

    public static <T> T getBeanByname(String c){
        if (staticApplicationContext != null) {
            return (T) staticApplicationContext.getBean(c);
        }
        return null;
    }
}
