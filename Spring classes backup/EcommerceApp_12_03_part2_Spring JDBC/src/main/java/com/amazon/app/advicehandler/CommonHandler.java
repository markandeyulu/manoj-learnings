package com.amazon.app.advicehandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.amazon.app.exceptions.ProductNotFoundException;

@ControllerAdvice // Single point of exception handling from multiple Back controllers
public class CommonHandler {
/*
	@ExceptionHandler({ProductNotFoundException.class}) // arg classes can be multiple with comma. Exception.class also can be given
	public String handleProductException( ) {
		return "error";
	}
	*/
	
	@ExceptionHandler({ProductNotFoundException.class}) // arg classes can be multiple with comma. Exception.class also can be given
	public ModelAndView handleProductException(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error", "ex", ex);//Spring concept. Same like web servlet Req dispatcher. Instead req.setattribute in servlet and get attribute in jsp, we can use this
		//you can have the same Exception handler in controller as well. But priority will be given to that BC instead of Common handler. Hirarchy is important.
		// parallel to this you can handle the exceptions in BC as well.
	
		//message will become dynamic in this case to JSP.
		
		/*mv.setViewName("error.jsp");
		mv.addObject("ex", ex);*///another way
		
		
		return mv;
	}
}
