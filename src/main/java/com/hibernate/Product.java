package com.hibernate;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Id;
	public String Name;
	public Date ExpiryDate;
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	public Category Category;
}