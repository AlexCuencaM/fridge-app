package com.fridge.domain.usecases.product;

import java.util.concurrent.CompletableFuture;

public interface IDeleteProducts {
	CompletableFuture<Void> execute(int productId);
}
