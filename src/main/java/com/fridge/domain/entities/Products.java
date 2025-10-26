package com.fridge.domain.entities;


public class Products {
	private int Id;
	private String Name;
	private ProductInventory productInventory;
	public Products(int id, String name) {
		Id = id;
		Name = name;
	}
	public Products() {
		// TODO Auto-generated constructor stub
		this.productInventory = new ProductInventory();
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public ProductInventory getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(ProductInventory productInventory) {
		this.productInventory = productInventory;
	}
	public java.util.Date getExpiryDate() {
		return this.productInventory.getExpiryDate();
	}
	public void setExpiryDate(java.util.Date expiryDate) {
		this.productInventory.setExpiryDate(expiryDate);
	}
}