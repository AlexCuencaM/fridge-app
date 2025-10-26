package com.fridge.infrastructure.datasources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.fridge.domain.datasources.IProductDatasource;
import com.fridge.domain.entities.Products;
import com.jdbc.DatabaseConnection;

public class ProductSqlServerDatasource implements IProductDatasource {

	@Override
	public CompletableFuture<Void> addProduct(Products p) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			String sql = "INSERT INTO Products (name, price, expiryDate) VALUES (?, ?, ?)";
			// Execute the SQL statement using JDBC or an ORM framework
			try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql)) {
		            ps.setString(1, p.getName());
		            ps.setDouble(2, p.getProductInventory().getPrice());

		            if (p.getExpiryDate() != null) {
		                ps.setDate(3, new java.sql.Date(p.getExpiryDate().getTime()));
		            } else {
		                ps.setNull(3, Types.DATE);
		            }
		            ps.executeUpdate();

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		});
	}

	@Override
	public CompletableFuture<Void> updateProduct(Products p) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			String sql = "UPDATE Products SET name=?, price=?, expiryDate=? WHERE id=?";
			// Execute the SQL statement using JDBC or an ORM framework
			try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, p.getName());
	            ps.setDouble(2, p.getProductInventory().getPrice());
	            if (p.getExpiryDate() != null) {
	                ps.setDate(3, new java.sql.Date(p.getExpiryDate().getTime()));
	            } else {
	                ps.setNull(3, Types.DATE);
	            }
	            ps.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		});
	}
	@Override
	public CompletableFuture<List<Products>> getAllProducts() {
		return CompletableFuture.supplyAsync(() -> {
			List<Products> list = new ArrayList<>();
			String sql = "SELECT id, name, price, expiryDate FROM Products ORDER BY id";
			try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql);
		             ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                Products p = new Products();
		                p.setId(rs.getInt("id"));
		                p.setName(rs.getString("name"));
		                p.getProductInventory().setPrice(rs.getDouble("price"));
		                Date expiry = rs.getDate("expiryDate");
		                if (expiry != null) p.setExpiryDate(new java.util.Date(expiry.getTime()));
		                list.add(p);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			return list;
		});
	}

	@Override
	public List<Products> getAllProductsSync() {
		// TODO Auto-generated method stub
		List<Products> list = new ArrayList<>();
		String sql = "SELECT id, name, price, expiryDate FROM Products ORDER BY id";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Products p = new Products();
	                p.setId(rs.getInt("id"));
	                p.setName(rs.getString("name"));
	                p.getProductInventory().setPrice(rs.getDouble("price"));
	                Date expiry = rs.getDate("expiryDate");
	                if (expiry != null) p.setExpiryDate(new java.util.Date(expiry.getTime()));
	                list.add(p);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return list;
	}
	@Override
	public Void addProductSync(Products p) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Products (name, price, expiryDate) VALUES (?, ?, ?)";
		// Execute the SQL statement using JDBC or an ORM framework
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, p.getName());
	            ps.setDouble(2, p.getProductInventory().getPrice());

	            if (p.getExpiryDate() != null) {
	                ps.setDate(3, new java.sql.Date(p.getExpiryDate().getTime()));
	            } else {
	                ps.setNull(3, Types.DATE);
	            }
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return null;
	}
	@Override
	public CompletableFuture<Void> deleteProduct(Products p) {
		// TODO Auto-generated method stub
		return CompletableFuture.runAsync(() -> {
			String sql = "DELETE FROM Products WHERE id=?";
			// Execute the SQL statement using JDBC or an ORM framework
			try (Connection conn = DatabaseConnection.getConnection();
		             PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, p.getId());
	            ps.executeUpdate();
	            ps.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		});
	}

	@Override
	public Products findProductById(int id) {
		// TODO Auto-generated method stub
		Products p = new Products();
		String sql = "SELECT id, name, price, expiryDate FROM Products WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                p.setId(rs.getInt("id"));
	                p.setName(rs.getString("name"));
	                p.getProductInventory().setPrice(rs.getDouble("price"));
	                Date expiry = rs.getDate("expiryDate");
	                if (expiry != null) p.setExpiryDate(new java.util.Date(expiry.getTime()));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return p;
	}
	
}