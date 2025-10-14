package com.fridge.domain.usecases.product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Product;
import com.fridge.domain.repositories.IProductRepository;

public class GetProducts implements IGetProducts {
	private IProductRepository _productRepository;

	public GetProducts(IProductRepository productRepository) {
		this._productRepository = productRepository;
	}

	@Override
	public CompletableFuture<List<Product>> execute() {
		return this._productRepository.getAllProducts();
	}
}
