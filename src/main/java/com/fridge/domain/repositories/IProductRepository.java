package com.fridge.domain.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;

public interface IProductRepository {
	CompletableFuture<Void> addProduct(Products product);
	CompletableFuture<Void> updateProduct(Products newProduct);
	CompletableFuture<Void> deleteProduct(Products product);
	CompletableFuture<List<Products>> getAllProducts();
	Products findProductById(int productId); 
	List<Products> getAllProductsSync();
}
