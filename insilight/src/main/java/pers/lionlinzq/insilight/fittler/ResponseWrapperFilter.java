package pers.lionlinzq.insilight.fittler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应包装器筛选器
 *
 * @author lin
 * @date 2025/05/05
 */
@Component
@Slf4j
public class ResponseWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("==========请求处理后执行==========");
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
        filterChain.doFilter(httpServletRequest, responseWrapper);

        // 可以在这里处理响应数据
        byte[] body = responseWrapper.getContentAsByteArray();
        // 处理body，例如添加签名
        responseWrapper.setHeader("X-Signature", "some-signature");

        // 必须调用此方法以将响应数据发送到客户端
        responseWrapper.copyBodyToResponse();
    }
}
