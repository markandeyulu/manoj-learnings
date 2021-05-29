package com.hibernate.demo;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_ {// java supports the "_" for static metamodel class

	// debugging is very easy compared to the normal query format
	public static volatile SingularAttribute<Product, Integer> prodId;// everything is wrapper type // this is in hibernate 5. how its in 4 - just analyse
	public static volatile SingularAttribute<Product, String> prodName;
	public static volatile SingularAttribute<Product, Float> prodPrice;
	
	//all these variables should be volatile and static
}
