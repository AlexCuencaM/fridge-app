package com.fridge.domain.usecases.product;

import com.fridge.domain.repositories.IProductRepository;

public class DeleteProducts implements IDeleteProducts {
	private IProductRepository _productRepository;
	public DeleteProducts(IProductRepository productRepository) {
		// TODO Auto-generated constructor stub
		_productRepository = productRepository;
	}
	@Override
	public java.util.concurrent.CompletableFuture<Void> execute(int productId) {
		// TODO Auto-generated method stub
		com.fridge.domain.entities.Products product = this._productRepository.findProductById(productId);
		if (product != null) {
			return this._productRepository.deleteProduct(product);
		}
		throw new IllegalArgumentException("Product with ID " + productId + " not found.");
		//return null;
	}

}
