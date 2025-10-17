package com.fridge.app.FridgeApp;

import java.util.concurrent.ExecutionException;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Products;
import com.fridge.domain.repositories.IProductRepository;
import com.fridge.domain.usecases.product.DeleteProducts;
import com.fridge.domain.usecases.product.GetProducts;
import com.fridge.domain.usecases.product.IDeleteProducts;
import com.fridge.domain.usecases.product.IGetProducts;
import com.fridge.domain.usecases.product.IPostProducts;
import com.fridge.domain.usecases.product.IPutProducts;
import com.fridge.domain.usecases.product.PostProducts;
import com.fridge.domain.usecases.product.PutProducts;
import com.fridge.infrastructure.datasources.ProductInMemoryDataSource;
import com.fridge.infrastructure.repositories.ProductRepository;

public class App 
{
    public static void main( String[] args )
    {
    	try {
    		System.out.println( "Welcome to the Fridge App!" );
        	IProductDatasource dataSource = new ProductInMemoryDataSource();
        	IProductRepository productRepository = new ProductRepository(dataSource);
        	IGetProducts getProducts = new GetProducts(productRepository);
        	IPostProducts postProducts = new PostProducts(productRepository);
        	IPutProducts putProducts = new PutProducts(productRepository);
        	IDeleteProducts deleteProducts = new DeleteProducts(productRepository);
        	
        	postProducts.executeSync(new Products(0, "Milk", new java.util.Date()));
        	postProducts.executeSync(new Products(1, "Eggs", new java.util.Date()));
        	postProducts.executeSync(new Products(2, "Meat", new java.util.Date()));
        	System.out.println("Querying products synchronously...");
        	getProducts.executeSync().forEach(product -> {
        		System.out.println("Product Name: " + product.getName() + ", Expiry Date: " + product.getExpiryDate());
        	});
        	
        	System.out.println("Modifying a product...");
			putProducts.execute(1, new Products(1, "Organic Eggs", new java.util.Date())).get();
			System.out.println("Querying products synchronously...");
			getProducts.executeSync().forEach(product -> {
        		System.out.println("Product Name: " + product.getName() + ", Expiry Date: " + product.getExpiryDate());
        	});
			
			System.out.println("Deleting a product...");
			deleteProducts.execute(0).get();
			System.out.println("Querying products synchronously...");
			getProducts.executeSync().forEach(product -> {
				System.out.println("Product Name: " + product.getName() + ", Expiry Date: " + product.getExpiryDate());
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
