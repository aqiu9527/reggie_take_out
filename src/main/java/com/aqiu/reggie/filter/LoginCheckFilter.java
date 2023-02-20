package com.aqiu.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.aqiu.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Powered by Aqiu On 2023--02--20--07--46--04
 *
 * @author Aqiu
 * @version 1.0
 * 檢查用戶是否已經完成登錄
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("拦截到路径：{}",requestURI);
        //定义一些不需要过滤的请求
        String[] urls = new String[]{
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**"
        };

        //判断本次请求是否需要处理
        boolean check = check(urls,requestURI);

        //不需要处理直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //判断是否登录过，登陆过放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户{}已经登陆",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }

        //没有登陆则返回未登录结果，通过输出流向客户端发送数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    /**
     * 检测本次请求是否需要放行
     * @return
     */
    public boolean check(String[] urls,String requestURL){
        for(String url : urls ){
            boolean match = PATH_MATCHER.match(url,requestURL);
            if(match){
                return true;
            }
        }
        return false;
    }

}
