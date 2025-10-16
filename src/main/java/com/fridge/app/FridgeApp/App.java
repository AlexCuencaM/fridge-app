package com.fridge.app.FridgeApp;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.repositories.IProductRepository;
import com.fridge.domain.usecases.product.GetProducts;
import com.fridge.domain.usecases.product.IGetProducts;
import com.fridge.infrastructure.datasources.ProductSqlServerDatasource;
import com.fridge.infrastructure.repositories.ProductRepository;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println( "Welcome to the Fridge App!" );
    	IProductDatasource dataSource = new ProductSqlServerDatasource();
    	IProductRepository productRepository = new ProductRepository(dataSource);
    	IGetProducts getProducts = new GetProducts(productRepository);
    	System.out.println("Fetching products synchronously...");
    	getProducts.executeSync().forEach(product -> {
    		System.out.println("Name: " + product.getName());
    	});
			/*getProducts.execute().thenAccept(products -> {
				System.out.println("Products in the fridge:" + products.size());
				products.forEach(product -> {
					System.out.println("Name: " + product.getName());
				});
			}).exceptionally(ex -> {
				System.err.println("Error fetching products: " + ex.getMessage());
				return null;
			}).get();*/
        
    }
}
