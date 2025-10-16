package com.fridge.domain.datasources;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;
public interface IProductDatasource {
	CompletableFuture<Void> addProduct(Products product);
	Void addProductSync(Products product);
	CompletableFuture<Void> updateProduct(Products product);
	CompletableFuture<Void> deleteProduct(int productId);
	CompletableFuture<List<Products>> getAllProducts();
	List<Products> getAllProductsSync();
}