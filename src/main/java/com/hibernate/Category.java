package com.hibernate;
import jakarta.persistence.*;
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Id;
	public String Name;
}
