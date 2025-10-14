package com.hibernate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Id;
	@NotNull
	public String Name;
}
