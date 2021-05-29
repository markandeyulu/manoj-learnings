package com.amazon.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.app.beans.Product;
import com.amazon.app.services.ProductService;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
//In deployment assembly we need to add the Maven dependency, webapp folder and others to run this web mvc application

//Back Controller
@Controller //@RestController will take care of the Controller
//@RestController // you dont need to add RestBody response. it will have internally
@RequestMapping("/product") //If URI's are unique you dont need identifier. this is just example identifier
public class ProductController {

	// If URI's are unique you dont need identifier
	//@ResponseBody// to append this with response header
	/*@RequestMapping(value = "/getproduct", method=RequestMethod.GET) //by default GET
*/	
/*	@GetMapping("/getproduct") // same way we have postmapping and rest. latest usage
	public String getMessage() {
		return "Welcome to Spring MVC App";
	}
*/	
	//doGet(HttpServletRequest, HttpServletResponse) //do we see this in this file ? NO - how do we do that in our code ?

/*	@GetMapping("/getproduct") // same way we have postmapping and rest. latest usage
	public String getMessage(HttpServletRequest request) { //dispatcher servlet will have internal doget and dopost. DS will forward the HttpServlet request to this line. This we can use this in BC as well.
		//All the operations available in DS will be available in BC.
		String name = request.getParameter("name");
		
		//HttpSession session = request.getSession(); //getting session is also possible
		return "Welcome to Spring MVC App" + name;
	}*/

/*	@ResponseBody // This is important to be here for REST req
	@GetMapping("/getproduct") 
	public String getMessage(@RequestParam String name) { // another way of getting request param
//String name whould match with the request param name 
//		other way of using is given in next block
		//String name = request.getParameter("name");
		
		return "Welcome to Spring MVC App" + name;
	}*/
	
	@ResponseBody // This is important to be here for REST req
	@GetMapping("/getproduct") 
	public String getMessage(@RequestParam ("name") String uname, @RequestParam int age) {  
//		other way of using is given in this block
		
		return "Welcome to Spring MVC App" + uname + (age+1);// This age will be treated as integer if you give @RequestParam int or Integer. If not this will be treated as String
		//By default what ever you pass to the controller is treated as String and converted to respective types. So even default values need to be set as String
		//@RequestParam(required=true,defaultValue="1") int var - defaultValue=1 will give you an error
		
	}
	
	@Autowired
	ProductService productService;
	
/*	@GetMapping("/product")
	@ResponseBody
	public List<Product> getProductDetails() {
		return productService.getAllProducts(); // browser will not understand this ArrayList - No converter found for return value of type: class java.util.ArrayList. We need to convert to XML or JSON
		// We need to use 3rd party library (jackson-dataformat) to help DS to convert ArrayList to JSON or XML
		//After adding the Jackson XML binding jar to Maven, DS will convert the object to XML/JSON and provide to browser, browser will provide JSON or XML response
		
	  	<dependency>
  		<groupId>com.fasterxml.jackson.dataformat</groupId>
  		<artifactId>jackson-dataformat-xml</artifactId>
  		<version>2.8.9</version>
  	</dependency>
  	
		
		// When you return a String it wont have an Issue even without Jackson jar. But when you return an Object it has to be converted to XML/JSON.
		
		//http://localhost:8758/EcommerceApp/product/product.json or http://localhost:8758/EcommerceApp/product/product(XML one)
	

	}*/
	
	@GetMapping("/product")
	@ResponseBody
	public List<Product> getProductDetails() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{productName}")//URI templating name - both path and url template is for diff purpose
	@ResponseBody
	public List<Product> getProductDetails(@PathVariable String productName) throws Exception {// if you want diff name give inside brackets @pathvariable ( "samplename" String productname)
		return productService.getAllProductsByName(productName);
	}
	
	
	@PostMapping("/insertproduct")//URI templating name - both path and url template is for diff purpose
	//@ResponseBody
	//@RequestBody - to read request envelope which will have request header
	public boolean insertProduct(@RequestBody Product product) {// if you want diff name give inside brackets @pathvariable ( "samplename" String productname)
		return productService.addProduct(product);
	}
	/*
	@ExceptionHandler(Exception.class)//unhandled exceptions will be handled here. 
	public String pleaseHandle(HttpServletRequest request, Exception e)//we can make this as no parameterised method as well
	{
		//return "/WEB-INF/views/error.jsp";//web assembly need to set with /webapp folder to access web pages - local config
		// This return statement will be as String "/WEB-INF/views/error.jsp" in browser if we use @RestController. because rest controller will consider this as string. If you use @controller you will be redirected to the respective jsp/html. In this case, we will have issue if we dont add @responsebody
		//view resolver can give option to give generic names. html/jsp etc
		return "error";// it will look for ViewResolver (in config file. in our case its InternalResourceViewResolver)
	}// it need to be added to individual BC ? use controller advice - common place
	*/ //This can be handled with the controller advice as well.
	
}
