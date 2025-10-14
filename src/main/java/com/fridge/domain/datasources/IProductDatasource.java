package com.fridge.domain.datasources;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Product;
public interface IProductDatasource {
	CompletableFuture<Void> addProduct(Product product);
	CompletableFuture<Void> updateProduct(Product product);
	CompletableFuture<Void> deleteProduct(int productId);
	CompletableFuture<List<Product>> getAllProducts();
}