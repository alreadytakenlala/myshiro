package top.lijunliang.permission.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import top.lijunliang.permission.ApplicationServer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 过滤器为了往header里面塞参数
 * @author Administrator
 *
 */
@WebFilter(urlPatterns = "/*")
public class ModifyParametersFilter extends OncePerRequestFilter
{
    protected static final Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        logger.info("Filter:添加跨域CORS");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        filterChain.doFilter(request, response);
    }
}
