package com.fridge.infrastructure.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Products;
import com.fridge.domain.repositories.IProductRepository;

public class ProductRepository implements IProductRepository {
	private IProductDatasource _dataSource;
	public ProductRepository (IProductDatasource dataSource) {
		// TODO Auto-generated constructor stub
		this._dataSource = dataSource;
	}
	
	@Override
	public CompletableFuture<Void> addProduct(Products product) {
		// TODO Auto-generated method stub
		return this._dataSource.addProduct(product);
	}
	@Override
	public CompletableFuture<Void> deleteProduct(Products product) {
		// TODO Auto-generated method stub
		return this._dataSource.deleteProduct(product);
	}
	@Override
	public CompletableFuture<List<Products>> getAllProducts() {
		// TODO Auto-generated method stub
		return this._dataSource.getAllProducts();
	}

	@Override
	public List<Products> getAllProductsSync() {
		// TODO Auto-generated method stub
		return this._dataSource.getAllProductsSync();
	}

	@Override
	public CompletableFuture<Void> updateProduct(Products product) {
		// TODO Auto-generated method stub
		return this._dataSource.updateProduct(product);
	}

	@Override
	public Products findProductById(int productId) {
		// TODO Auto-generated method stub
		return this._dataSource.findProductById(productId);
	}

}
