package com.amazon.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.amazon.app.dao.ProductDAO;

@Configuration
@EnableWebMvc//this will tell container that instead xml we r gonna use java based configuration. This will be ony used to specify for webMVC not in spring core applications
@ComponentScan
public class WebConfigFile implements WebMvcConfigurer {

	

	@Bean("p1")
	//@Profile("testing")
	public ProductDAO getProductDAO1() {
		return new ProductDAO("Testing");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCorsMappings(CorsRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFormatters(FormatterRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
// To read request and get response in JSON
		
		// Web related configuration. not Sprinig configuration. 
		// This will be taken by AnnotationCOnfigContext and give to container.
		// This need to be overridden to tell Controller to give Json and not XML
		configurer.favorPathExtension(true)//to give .json in request
		.favorParameter(true)//if you feel ackward giving .json, we can give param=json
		.parameterName("mediaType")//?mediaType=xml
		.ignoreAcceptHeader(false)//application/text, etc
		.defaultContentType(MediaType.APPLICATION_JSON)//how this works ? - http://localhost:8758/EcommerceApp/product/product/IPad we will get XML. This is for Spriong side. But Jackson will give default as XML.
		.mediaType("xml", MediaType.APPLICATION_XML)//http://localhost:8758/EcommerceApp/product/product/IPad?mediaType=xml
		.mediaType("json", MediaType.APPLICATION_JSON);//http://localhost:8758/EcommerceApp/product/product/IPad?mediaType=json
		// He will instruct only. But conversion will be done only by Jackson(3rd party).
		// This will be taken by AnnotationCOnfigContext and give to container. container to DS and DS will give to Jackson
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Bean
	public InternalResourceViewResolver getResolved() {//there are many ViewResolvers. we are using this.
		// Go for this only If we follow the singular patter. If you have diff path we cant do this.
		
		//The below code from controller will call this.
		/*
		 * 	@ExceptionHandler(Exception.class)//unhandled exception will be handled here.
	public String pleaseHandle(HttpServletRequest request, Exception e)
	{
		//return "/WEB-INF/views/error.jsp";//web assembly
		//view resolver can give option to give generic names. html/jsp etc
		return "error";
	}
		 */
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
}
