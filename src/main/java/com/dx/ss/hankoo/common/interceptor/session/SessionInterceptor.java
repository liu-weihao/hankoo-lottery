package com.dx.ss.hankoo.common.interceptor.session;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SessionInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 暂未登录
	 */
	public abstract void notLogin(HttpServletRequest request, HttpServletResponse response, Object handler);
	
	/**
	 * 登录过期
	 */
	public abstract void loginExpire(HttpServletRequest request, HttpServletResponse response, Object handler);
	
	/**
	 * 未授权访问
	 */
	public abstract void notAuthorized(HttpServletRequest request, HttpServletResponse response, Object handler);

	/**
	 * 该方法将在请求处理之前进行调用。
	 * 如果已经是最后一个Interceptor的时候，就会调用当前请求的Controller方法。
	 * @return 当返回为false时，表示请求结束，后续的Interceptor和Controller都不会再执行；
	 * 当返回为true时，将会继续调用下一个Interceptor 的preHandle 方法。
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return super.preHandle(request, response, handler);
	}

	/**
	 * 在当前请求进行处理之后，也就是Controller 方法调用之后执行，
	 * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用，
	 * 所以我们可以在这个方法中对Controller处理之后的ModelAndView对象进行操作。
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。
	 * 这个方法的主要作用是用于进行资源清理工作的。
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object, Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		super.afterCompletion(request, response, handler, ex);
	}
}
