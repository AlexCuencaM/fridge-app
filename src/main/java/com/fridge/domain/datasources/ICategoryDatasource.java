package com.fridge.domain.datasources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Category;

public interface ICategoryDatasource {
	CompletableFuture<Void> addCategory(Category category);
	CompletableFuture<List<Category>> getAllCategories();
}
