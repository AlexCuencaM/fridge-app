package com.fridge.infrastructure.datasources;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.hibernate.Session;

import com.fridge.data.sqlserver.FridgeContext;
import com.fridge.domain.datasources.IProductDatasource;
//import com.fridge.domain.entities.Categories;
import com.fridge.domain.entities.Products;
import com.hibernate.Product;
public class ProductSqlServerDatasource implements IProductDatasource {

	@Override
	public CompletableFuture<Void> addProduct(Products product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<Void> updateProduct(Products product) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public CompletableFuture<List<Products>> getAllProducts() {
		return CompletableFuture.supplyAsync(() -> {
			Session session = FridgeContext.getSessionFactory().openSession();
			List<Product> productsDb = session.createQuery(
				"SELECT p FROM Product p", Product.class).getResultList();
			// Map before closing session to avoid LazyInitializationException
			List<Products> domainProducts = productsDb.stream().map(p -> {
				Products domainProduct = new Products();
				domainProduct.setName(p.Name);
				domainProduct.setExpiryDate(p.ExpiryDate);
				/*if (p.Category != null) {
					Category domainCategory = new Category();
					domainCategory.setId(p.Category.Id);
					domainCategory.setName(p.Category.Name);
					domainProduct.setCategory(domainCategory);
				}*/
				return domainProduct;
			}).toList();
			session.close();
			return domainProducts;
		});
	}

	@Override
	public List<Products> getAllProductsSync() {
		// TODO Auto-generated method stub
		Session session = FridgeContext.getSessionFactory().openSession();
		List<Product> productsDb = session.createQuery(
			"SELECT p FROM Product p", Product.class).getResultList();
		// Map before closing session to avoid LazyInitializationException
		List<Products> domainProducts = productsDb.stream().map(p -> {
			Products domainProduct = new Products();
			domainProduct.setName(p.Name);
			domainProduct.setExpiryDate(p.ExpiryDate);
			/*if (p.Category != null) {
				Category domainCategory = new Category();
				domainCategory.setId(p.Category.Id);
				domainCategory.setName(p.Category.Name);
				domainProduct.setCategory(domainCategory);
			}*/
			return domainProduct;
		}).toList();
		session.close();
		return domainProducts;
	}

	@Override
	public Void addProductSync(Products product) {
		// TODO Auto-generated method stub
		Session session = FridgeContext.getSessionFactory().openSession();
		session.beginTransaction();
		Product dbProduct = new Product();
		dbProduct.Name = product.getName();
		dbProduct.ExpiryDate = product.getProductInventory().getExpiryDate();
		session.getTransaction().commit();
		session.getSessionFactory().close();
		return null;
		
		
	}

	@Override
	public CompletableFuture<Void> deleteProduct(Products product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Products findProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}