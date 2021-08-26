package com.cp.sendmq.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by georgy on 2018/11/21.
 */
@Component
@WebFilter(filterName = "traceIdFilter", urlPatterns = "/*")
public class TraceIdFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {

            String traceId = request.getHeader(TRACE_ID);
            //MDC.put(TRACE_ID, StringUtils.isNotBlank(traceId) ? traceId : IdWorker.getIdStr().substring(0,16));
            MDC.put("traceId", "555555555555555");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            logger.error("traceId_error: {}", e);
            throw new RuntimeException(e);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }


}
