package com.vaya.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vaya.general.interceptors.BaseInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/resources/**");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("auth/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);		
	}
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BaseInterceptor());
	}
	/*
	 * This bean catches error and redirects.
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	        	ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/error401");
	        	ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/error/error403");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/error404");
	            //ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/error500");
	            container.addErrorPages(error401Page, error403Page, error404Page);
	        }
	    };
	}
}
