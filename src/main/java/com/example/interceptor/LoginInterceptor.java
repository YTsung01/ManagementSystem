package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.entity.oldEmployee;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		// 檢查 session 中是否有 user 的物件資料(意味著用戶已經登入)
		if(session.getAttribute("employee") != null) {
			oldEmployee employee = (oldEmployee)session.getAttribute("employee");
			// 路徑的權限檢查
			// "/group_buy/backend", user level = 2 才可以進入
			System.out.println("RequestURI = " + request.getRequestURI());
			if(request.getRequestURI().contains("/boss")) { // 後台
				if(employee.getLevelId() == 2) {
					return true; // 放行
				} else {
					response.sendRedirect(request.getServletContext().getContextPath() + "/app/login");
					return false; // 不放行
				}
			} 
			return true; // 放行
		}
		// 未登入, 導入到登入頁面
		response.sendRedirect(request.getServletContext().getContextPath() + "/app/login");
		return false; // 不放行
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
