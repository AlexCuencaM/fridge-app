package com.fridge.domain.datasources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.entities.Categories;

public interface ICategoryDatasource {
	CompletableFuture<Void> addCategory(Categories category);
	CompletableFuture<List<Categories>> getAllCategories();
}
