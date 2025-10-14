package com.fridge.infrastructure.datasources;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.hibernate.Session;

import com.fridge.data.sqlserver.FridgeContext;
import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Product;

public class ProductSqlServerDatasource implements IProductDatasource {

	@Override
	public CompletableFuture<Void> addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Void> updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Void> deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CompletableFuture<List<Product>> getAllProducts() {
		return CompletableFuture.supplyAsync(() -> {
			Session session = FridgeContext.getSessionFactory().openSession();
			List<com.hibernate.Product> productsDb = session.createQuery(
				"SELECT p FROM Product p JOIN FETCH p.Category", com.hibernate.Product.class).list();
			// Map before closing session to avoid LazyInitializationException
			List<Product> domainProducts = productsDb.stream().map(p -> {
				Product domainProduct = new Product();
				domainProduct.setName(p.Name);
				domainProduct.setExpiryDate(p.ExpiryDate);
				if (p.Category != null) {
					com.fridge.domain.entities.Category domainCategory = new com.fridge.domain.entities.Category();
					domainCategory.setId(p.Category.Id);
					domainCategory.setName(p.Category.Name);
					domainProduct.setCategory(domainCategory);
				}
				return domainProduct;
			}).toList();
			session.close();
			return domainProducts;
		});
	}
	
}