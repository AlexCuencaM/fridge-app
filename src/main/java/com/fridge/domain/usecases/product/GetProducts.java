package com.fridge.domain.usecases.product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;
import com.fridge.domain.repositories.IProductRepository;

public class GetProducts implements IGetProducts {
	private IProductRepository _productRepository;

	public GetProducts(IProductRepository productRepository) {
		this._productRepository = productRepository;
	}

	@Override
	public CompletableFuture<List<Products>> execute() {
		return this._productRepository.getAllProducts();
	}

	@Override
	public List<Products> executeSync() {
		// TODO Auto-generated method stub
		return this._productRepository.getAllProductsSync();
	}
}
