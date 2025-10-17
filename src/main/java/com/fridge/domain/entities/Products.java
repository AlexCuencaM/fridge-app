package com.fridge.domain.entities;

import java.util.Date;

public class Products {
	private int Id;
	private String Name;
	private Date ExpiryDate; 
	private Categories Category;
	public Products(int id, String name, Date expiryDate) {
		Id = id;
		Name = name;
		ExpiryDate = expiryDate;
	}
	public Products() {
		// TODO Auto-generated constructor stub
	}
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
	public Categories getCategory() {
		return Category;
	}
	public void setCategory(Categories category) {
		Category = category;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
}