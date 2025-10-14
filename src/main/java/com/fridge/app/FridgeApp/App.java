package com.fridge.app.FridgeApp;

import java.util.concurrent.ExecutionException;

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
        try {
        	System.out.println( "Welcome to the Fridge App!" );
        	IProductDatasource dataSource = new ProductSqlServerDatasource();
        	IProductRepository productRepository = new ProductRepository(dataSource);
        	IGetProducts getProducts = new GetProducts(productRepository);
			getProducts.execute().thenAccept(products -> {
				System.out.println("Products in the fridge:" + products.size());
				products.forEach(product -> {
					System.out.println("Name: " + product.getName());
				});
			}).exceptionally(ex -> {
				System.err.println("Error fetching products: " + ex.getMessage());
				return null;
			}).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
