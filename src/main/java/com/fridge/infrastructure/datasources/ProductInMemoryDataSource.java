package com.fridge.infrastructure.datasources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Products;

public class ProductInMemoryDataSource implements IProductDatasource {
	private List<Products> products;
	public ProductInMemoryDataSource() {
		products = new ArrayList<Products>();
	}
	
	@Override
	public CompletableFuture<Void> addProduct(Products product) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			products.add(product);
		});
	}

	@Override
	public Void addProductSync(Products product) {
		// TODO Auto-generated method stub
		products.add(product);
		return null;
	}
	@Override
	public Products findProductById(int id) {
		for (Products product : products) {
			if (product.getId() == id) { // Assuming Products has a method like
				return product;
			}
		}
		throw new RuntimeException(String.format("Product '%s' not found", id) );
	}
	@Override
	public CompletableFuture<Void> updateProduct(Products product) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			int index = products.indexOf(product);
			if (index != -1) {
				products.set(index, product);
			}
		});
	}

	@Override
	public CompletableFuture<Void> deleteProduct(Products product) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			products.remove(product);
		});
	}

	@Override
	public CompletableFuture<List<Products>> getAllProducts() {
		// TODO Auto-generated method stub
		return CompletableFuture.supplyAsync(() -> {
			return products;
		});
	}

	@Override
	public List<Products> getAllProductsSync() {
		// TODO Auto-generated method stub
		return products;
	}

	
}
