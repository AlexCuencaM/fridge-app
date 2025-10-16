package com.fridge.domain.usecases.product;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import com.fridge.domain.entities.Products;

public interface IGetProducts {
	CompletableFuture<List<Products>> execute();
	List<Products> executeSync();
}
