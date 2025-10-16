package com.fridge.domain.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Categories;

public interface ICategoryRepository {
	CompletableFuture<Void> addCategory(Categories category);
	CompletableFuture<List<Categories>> getAllCategories();
}
