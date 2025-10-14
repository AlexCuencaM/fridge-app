package com.fridge.infrastructure.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Product;
import com.fridge.domain.repositories.IProductRepository;

public class ProductRepository implements IProductRepository {
	private IProductDatasource _dataSource;
	public ProductRepository (IProductDatasource dataSource) {
		// TODO Auto-generated constructor stub
		this._dataSource = dataSource;
	}
	
	@Override
	public CompletableFuture<Void> addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Void> updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Void> deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CompletableFuture<List<Product>> getAllProducts() {
		// TODO Auto-generated method stub
		return this._dataSource.getAllProducts();
	}

}
