package pers.lionlinzq.insilight.fittler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求包装筛选器
 *
 * @author lin@date 2025/05/05
 */
@Slf4j
@Component
public class RequestWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("==========请求处理前执行=======");
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        // 可以在这里处理请求数据
        byte[] body = requestWrapper.getContentAsByteArray();
        // 处理body，例如记录日志
        //。。。
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }
}
