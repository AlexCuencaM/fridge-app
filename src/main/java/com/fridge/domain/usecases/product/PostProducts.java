package com.fridge.domain.usecases.product;

import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;
import com.fridge.domain.repositories.IProductRepository;

public class PostProducts implements IPostProducts {
	private IProductRepository _productRepository;
	public PostProducts(IProductRepository productRepository) {
		// TODO Auto-generated constructor stub
		this._productRepository = productRepository;
	}
	@Override
	public CompletableFuture<Void> executeSync(Products newProduct) {
		// TODO Auto-generated method stub
		
		return _productRepository.addProduct(newProduct);
	}

}
