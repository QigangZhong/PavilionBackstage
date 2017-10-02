package com.pavilion.filter;

import com.pavilion.dao.MenuMapper;
import com.pavilion.dao.RoleMapper;
import com.pavilion.dao.RoleMenuMapper;
import com.pavilion.domain.Menu;
import com.pavilion.domain.Role;
import com.pavilion.domain.RoleMenu;
import com.pavilion.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Order(1)
@WebFilter(filterName = "PrivilegeFilter", urlPatterns = "/*")
public class PrivilegeFilter implements Filter {
    Logger logger= LoggerFactory.getLogger(PrivilegeFilter.class);

    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("PrivilegeFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url=((HttpServletRequest)servletRequest).getRequestURI();
        if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".ico") || url.endsWith(".jpg")|| url.endsWith(".png") || url.endsWith("woff")){
            //资源文件放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String method=((HttpServletRequest)servletRequest).getMethod().toLowerCase();

        int userId=0;
        HttpSession session=((HttpServletRequest)servletRequest).getSession();
        if (session!=null || session.getAttribute("user")!=null){
            User user=(User) session.getAttribute("user");
            if (user!=null){
                userId=user.getId();
            }
        }

        //查找当前路径的菜单
        Menu menu=menuMapper.selectByUrl(url,method);
        if(menu==null){
            //菜单没有配置则放行
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }else{
            int menuId=menu.getId();
            //查找当前用户的角色是否已设置当前菜单权限
            List<RoleMenu> ps=roleMenuMapper.selectByUserIdAndMenuId(userId,menuId);
            if(ps==null || ps.size()<=0){
                //没有权限,直接返回
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "forbidden.");
            }else{
                //当前用户对应的角色已配置该菜单,则放行
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        logger.info("PrivilegeFilter destroy...");
    }
}
