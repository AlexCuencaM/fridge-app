package com.fridge.domain.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;

public interface IProductRepository {
	CompletableFuture<Void> addProduct(Products product);
	CompletableFuture<Void> updateProduct(Products product);
	CompletableFuture<Void> deleteProduct(int productId);
	CompletableFuture<List<Products>> getAllProducts();
	List<Products> getAllProductsSync();
}
