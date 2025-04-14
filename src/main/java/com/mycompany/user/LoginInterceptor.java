package com.mycompany.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//创建登录拦截器类
public class LoginInterceptor implements HandlerInterceptor {
	
	// 排除拦截的URL路径
    static final String[] EXCLUDE_PATHS = {
        "/",          // 登录页
		"/login",          // 登录页
        "/users/login",          // 登录页
        "/users/register",       // 注册页
        "/users/save",       // 注册页
        "/assets/css/**",         // 静态资源
        "/assets/js/**",
        "/assets/images/**",
        "/img/**"
    };
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("uname");
        if (session == null || user == null) {
            // 未登录，重定向到登录页
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        
     // 权限验证
//        if (request.getRequestURI().startsWith("/admin") 
//            && !hasAdminRole(request.getSession())) {
//            response.sendError(HttpStatus.FORBIDDEN.value());
//            return false;
//        }
        
        return true;
    }

}
