package com.poke.www.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poke.www.interceptor.LoginCheckInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

	String uploadPath = "file:///C:\\_poke\\_project\\_fileUpload\\";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns(
				"/", "/login", "/logout", "/board/list",
				"/js/**", "/error", "/css/**", "/font/**", "/image/**"
				);
	}

}
