package com.packt.webstore.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * This is a mapped interceptor that is used in order to achieve conditional
 * redirecting What makes this a "mapped" interceptor is the
 * <code>registry.addInterceptor(promoCodeInterceptor()).addPathPatterns("")</code>
 * line in WebApplicationContextConfig class, which essentially adds a request
 * mapping to this interceptor
 * 
 * @author Sakel
 *
 */
public class PromoCodeInterceptor extends HandlerInterceptorAdapter {
	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		String givenPromoCode = request.getParameter("promo");
		if (promoCode.equals(givenPromoCode)) {
			response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
		} else {
			response.sendRedirect(errorRedirect);
		}
		return false;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}

	public void setOfferRedirect(String offerRedirect) {
		this.offerRedirect = offerRedirect;
	}
}