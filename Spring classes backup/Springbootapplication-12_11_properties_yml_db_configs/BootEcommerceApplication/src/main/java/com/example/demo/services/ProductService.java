package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Product;
import com.example.demo.repository.ProductRepository;

//@Component we can use @service. Nothing harm. Just to precise in what this is doing
@Service
public class ProductService {

	/*Spring JDBC Template code
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 * 
	 * 
	 * public List<Product> getAllProductsFromDB() {
	 * 
	 * return jdbcTemplate.query("SELECT * FROM products",(rs, rowNum) -> new
	 * Product( rs.getInt("prodId"), rs.getString("prodName"),
	 * rs.getFloat("prodPrice"))); }
	 */
	
	@Autowired 
	ProductRepository productRepository;
	
    @PersistenceContext
    private EntityManager entityManager;
    
	public List<Product> getAllProductsFromDB() {
		return productRepository.findAll();
	}
	
	public Product getProductById(int id) {
		Optional<Product> op = productRepository.findById(id);
		return op.get();
	}
	
	public List<Product> findAllActiveProductsNative(){
		return productRepository.findAllActiveProductsNative().stream().collect(Collectors.toList());
	}
	
	public Product getProductByNamedNativeQuery(String name) {
		Query q = entityManager.createNamedQuery("Product.findByName");
		q.setParameter(1, name+'%');
		return (Product) q.getSingleResult();
		//return null;
	}
	
	/*
	 * public Product getProductByOne(int id) { //ExampleMatcher
	 * caseInsensitiveExampleMatcher =
	 * ExampleMatcher.matchingAll().withIgnoreCase(); Example<Product> example =
	 * Example.of(Product.from(1, "Bloggs", null)); caseInsensitiveExampleMatcher);
	 * 
	 * Optional<Product> op = productRepository.findOne(example)(id); return
	 * op.get(); }
	 */
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(Product product) {
		productRepository.delete(product);
	}
	
	public void deleteById(int id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> findByCriteria(String value){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);

        Path<String> name = product.get("name");
        Predicate predicate = cb.like(name, value);
        query.select(product)
        .where(predicate);
        
        return entityManager.createQuery(query)
                .getResultList();
	}
	
	public List<Product> getAllProducts() {
		
		List<Product> products = new ArrayList<>();
		
		products.add(new Product(101,"Watch", 435.35f));
		products.add(new Product(102,"Wallet", 123.35f));
		products.add(new Product(103,"Mobile", 45.35f));
		products.add(new Product(104,"IPad", 3453.35f));
		
		
		return products;
	}
	
	
}
