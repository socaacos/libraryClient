package com.example.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.library.dtos.ReportDto;
import com.example.library.services.ReportService;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

	@Autowired
	ReportService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String req = request.getRequestURI();
		int resStatus = response.getStatus();
		ReportDto reportDto = new ReportDto(req, resStatus);
		service.create(reportDto);
		System.out.println(request.getRequestURI());
		System.out.println(response.getStatus());
	
	}
	
	

}
