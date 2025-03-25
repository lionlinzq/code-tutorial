package pers.lionlinzq.excel.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TimeCostInterceptor timeCostInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 明确指定拦截路径（如所有请求）
        registry.addInterceptor(timeCostInterceptor)
                .addPathPatterns("/**")  // 拦截所有URL
                .excludePathPatterns("/static/**"); // 排除静态资源
    }

}
