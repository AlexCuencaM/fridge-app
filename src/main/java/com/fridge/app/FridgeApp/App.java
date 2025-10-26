package com.fridge.app.FridgeApp;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.repositories.IProductRepository;
import com.fridge.domain.usecases.product.DeleteProducts;
import com.fridge.domain.usecases.product.GetProducts;
import com.fridge.domain.usecases.product.IDeleteProducts;
import com.fridge.domain.usecases.product.IGetProducts;
import com.fridge.domain.usecases.product.IPostProducts;
import com.fridge.domain.usecases.product.IPutProducts;
import com.fridge.domain.usecases.product.PostProducts;
import com.fridge.domain.usecases.product.PutProducts;
import com.fridge.infrastructure.datasources.ProductInMemoryDataSource;
import com.fridge.infrastructure.repositories.ProductRepository;
import com.fridge.presentation.GUI.GuiServer;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println( "Welcome to the Fridge App!" );
		IProductDatasource dataSource = new ProductInMemoryDataSource();
		IProductRepository productRepository = new ProductRepository(dataSource);
		IGetProducts getProducts = new GetProducts(productRepository);
		IPostProducts postProducts = new PostProducts(productRepository);
		IPutProducts putProducts = new PutProducts(productRepository);
		IDeleteProducts deleteProducts = new DeleteProducts(productRepository);
		
		GuiServer guiServer = new GuiServer(getProducts, postProducts, putProducts, deleteProducts);
		
		guiServer.start();
    }
}
