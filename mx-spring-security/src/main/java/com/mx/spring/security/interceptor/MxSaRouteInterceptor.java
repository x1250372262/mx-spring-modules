package com.mx.spring.security.interceptor;

import cn.dev33.satoken.exception.BackResultException;
import cn.dev33.satoken.exception.StopMatchException;
import cn.dev33.satoken.router.SaRouteFunction;
import cn.dev33.satoken.servlet.model.SaRequestForServlet;
import cn.dev33.satoken.servlet.model.SaResponseForServlet;
import cn.dev33.satoken.stp.StpUtil;
import com.mx.spring.security.annotation.NoLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MxSaRouteInterceptor implements HandlerInterceptor {

	public SaRouteFunction function = (req, res, handler) -> StpUtil.checkLogin();

	/**
	 * 创建一个路由拦截器
	 */
	public MxSaRouteInterceptor() {
	}

	public MxSaRouteInterceptor(SaRouteFunction function) {
		this.function = function;
	}

	public static MxSaRouteInterceptor newInstance(SaRouteFunction function) {
		return new MxSaRouteInterceptor(function);
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				NoLogin annotation = method.getAnnotation(NoLogin.class);
				if (annotation != null) {
					return true;
				}
				annotation = method.getDeclaringClass().getAnnotation(NoLogin.class);
				if (annotation != null) {
					return true;
				}
			}
			function.run(new SaRequestForServlet(request), new SaResponseForServlet(response), handler);
		} catch (StopMatchException e) {
			// 停止匹配，进入Controller 
		} catch (BackResultException e) {
			// 停止匹配，向前端输出结果 
			if(response.getContentType() == null) {
				response.setContentType("text/plain; charset=utf-8"); 
			}
			response.getWriter().print(e.getMessage());
			return false;
		}
		
		// 通过验证 
		return true;
	}

}
