package com.pavilion.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "PrivilegeFilter", urlPatterns = "/*")
public class PrivilegeFilter implements Filter {
    Logger logger= LoggerFactory.getLogger(PrivilegeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("PrivilegeFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path=((HttpServletRequest)servletRequest).getRequestURI();


        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("PrivilegeFilter destroy...");
    }
}
