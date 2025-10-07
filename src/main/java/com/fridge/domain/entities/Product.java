package com.fridge.domain.entities;
import java.util.Date;
public class Product {
	private String Name;
	private Date ExpiryDate; 
	private Category Category;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}
	public Category getCategory() {
		return Category;
	}
	public void setCategory(Category category) {
		Category = category;
	}
}
