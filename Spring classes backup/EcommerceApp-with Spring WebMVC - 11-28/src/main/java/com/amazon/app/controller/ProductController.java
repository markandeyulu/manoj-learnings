package com.amazon.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//Back Controller
@Controller
@RequestMapping("/product") //If URI's are unique you dont need identifier. this is just example identifier
public class ProductController {

	// If URI's are unique you dont need identifier
	@ResponseBody// to append this with response header
	/*@RequestMapping(value = "/getproduct", method=RequestMethod.GET) //by default GET
*/	
	@GetMapping("/getproduct") // same way we have postmapping and rest. latest usage
	public String getMessage() {
		return "Welcome to Spring MVC App";
	}
}
