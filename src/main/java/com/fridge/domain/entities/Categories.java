package com.fridge.domain.entities;

public class Category {
	private int Id;
	
	private String Name;
	
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
}
