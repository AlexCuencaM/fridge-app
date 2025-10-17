package com.fridge.domain.usecases.product;

import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Products;

public interface IPostProducts {
	CompletableFuture<Void> executeSync(Products newProduct); 
}
