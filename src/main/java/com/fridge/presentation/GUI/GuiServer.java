package com.fridge.presentation.GUI;

import org.eclipse.swt.widgets.Display;

import com.fridge.domain.usecases.product.IDeleteProducts;
import com.fridge.domain.usecases.product.IGetProducts;
import com.fridge.domain.usecases.product.IPostProducts;
import com.fridge.domain.usecases.product.IPutProducts;

public class GuiServer {
	private IGetProducts getProducts;
	private IPostProducts postProducts;
	private IPutProducts putProducts;
	private IDeleteProducts deleteProducts;
	public GuiServer(IGetProducts getProducts, IPostProducts postProducts, 
			IPutProducts putProducts, IDeleteProducts deleteProducts) {
		this.getProducts = getProducts;
		this.postProducts = postProducts;
		this.putProducts = putProducts;
		this.deleteProducts = deleteProducts;
	}
	public void start() {
		Display display = new Display();
        new ProductView(display, getProducts, postProducts, putProducts, deleteProducts);
        display.dispose();
	}
}
