package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 支持国际化的消息工具类
 *
 * @author rongdi
 * @create 2016-12-21 11:02
 **/
@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    public String get(String name) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(name, null, locale);
    }

}
