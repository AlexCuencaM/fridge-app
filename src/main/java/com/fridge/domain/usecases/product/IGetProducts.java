package com.fridge.domain.usecases.product;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.fridge.domain.entities.Product;

public interface IGetProducts {
	CompletableFuture<List<Product>> execute();
}
