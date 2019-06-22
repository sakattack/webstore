package com.packt.webstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * if you do not want to implement all 3 mandatory methods (preHandle,
 * postHandle, afterCompletion), then instead you can extend a default
 * implementation (HandlerInterceptorAdapter) of HandlerInterceptor
 * 
 * also, log4j config is in file src/main/resources/log4j.properties
 */
public class ProcessingTimeLogInterceptor implements HandlerInterceptor {
	private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
		String path = request.getRequestURL() + queryString;
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		LOGGER.info(String.format("%s millisecond taken to process the request %s.", (endTime - startTime), path));
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exceptionIfAny) {
// NO operation.
	}
}