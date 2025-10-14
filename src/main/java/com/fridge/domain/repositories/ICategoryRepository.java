package com.fridge.domain.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Category;

public interface ICategoryRepository {
	CompletableFuture<Void> addCategory(Category category);
	CompletableFuture<List<Category>> getAllCategories();
}
