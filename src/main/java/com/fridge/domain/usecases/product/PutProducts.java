package com.fridge.domain.usecases.product;

import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;
import com.fridge.domain.repositories.IProductRepository;

public class PutProducts implements IPutProducts {
	private IProductRepository _productRepository;
	public PutProducts(IProductRepository productRepository) {
		// TODO Auto-generated constructor stub
		_productRepository = productRepository;
	}
	@Override
	public CompletableFuture<Void> execute(int productId, Products newProduct) {
		// TODO Auto-generated method stub
		Products product = this._productRepository.findProductById(productId);
		product.setName(newProduct.getName());
        product.setExpiryDate(newProduct.getProductInventory().getExpiryDate());
		//product.setCategory(newProduct.getCategory());
		return this._productRepository.updateProduct(product);
	}

}