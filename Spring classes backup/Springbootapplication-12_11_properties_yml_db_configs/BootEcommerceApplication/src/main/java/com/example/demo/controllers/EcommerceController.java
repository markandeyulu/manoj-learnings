package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Product;
import com.example.demo.services.OfferService;
import com.example.demo.services.ProductService;

@RestController
public class EcommerceController {

/*	@Value("${product.offer}")//SPEL //profile value from application-dev.properties. 
 * you have to add this in bootclass(BootEcommerceApplication) to let Boot know we have more than one prop file. 
 * thats y its an array - @PropertySource(value = {"application-dev.properties"})
	private float offerPrice;
	//IOC container cant recognise this value - java.lang.IllegalArgumentException: Could not resolve placeholder 'product.offer' in value "${product.offer}"
	//it will pick only application.properties or application.yml. So we need to @resource
	
	@GetMapping("/offer")
	public float getOfferPrice() {
		
		return offerPrice;
	}
	*/
	
	
	@Autowired
	OfferService offerService;
	
	
/*	@GetMapping("/offer")
	public float getOfferPrice() {
		
		return offerService.getOfferPrice();
	}*///commented to see Boot property read approach
	
	@GetMapping("/offer")
	public String getOfferPrice() {
		return offerService.getOffer()+"Available till" + offerService.getExpiry()+"Days only:: "+offerService.getName()+" == "+offerService.getDesc()+" == "+offerService.getRemarks();
	}
	
	@Autowired
	ProductService productService;
	@GetMapping("/products")
	public List<Product> getProductDetails() {
		return productService.getAllProducts(); // why .xml not working in browser ?. We have to do content negotiation as we did in config file in Spring. we can do this in properties. and add jackson bind jar(spring boot looking for 2.9.7) as well. // if you add 2.9.6 its not working
		
		//Boot gives default as JSON only
		
		/* instead of java code,
		# same like what we did in Spring contentnegotiation
		spring.mvc.contentnegotiation.favor-path-extension=true
		spring.mvc.contentnegotiation.favor-parameter=true
		spring.mvc.contentnegotiation.parameter-name=mediaType
		spring.mvc.pathmatch.use-registered-suffix-pattern=true*/
		
	}
	
	@GetMapping("/dbProducts")
	public List<Product> getDBProductDetails() {
		return productService.getAllProductsFromDB();
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/save")
	public Product saveProduct() {
		Product product = new Product(3, "Java", 11254.0f);
		return productService.saveProduct(product);
	}
	
	@GetMapping("/update")
	public Product updateProduct() {
		Product product = new Product(3, "Java", 0.0f);
		return productService.saveProduct(product);
	}
	
	@GetMapping("/delete")
	public List<Product> deleteProduct() {
		Product product = new Product(3, "PHP", 0.0f);
		productService.delete(product);
		
		return productService.getAllProductsFromDB();
	}
	
	@GetMapping("/delete/{id}")
	public List<Product> deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
		return productService.getAllProductsFromDB();
	}
	
	@GetMapping("/findByCriteria/{name}")
	public List<Product> findByCriteria(@PathVariable String name) {
		//productService.deleteById(id);
		return productService.findByCriteria(name);
	}
	
	@GetMapping("/findAllActiveProductsNative")
	public List<Product> findAllActiveProductsNative() {
		return productService.findAllActiveProductsNative();
	}
	
	@GetMapping("/getProductByNamedNativeQuery/{name}")
	public Product getProductByNamedNativeQuery(@PathVariable String name) {
		//productService.deleteById(id);
		return productService.getProductByNamedNativeQuery(name);
	}
	
	//SOLID, S stands for Single responsibility for a particular class. But we are doing SPEL. but we should only have controller ideally by SOLID approach. so move the code to OfferService.
}
